import {
  Component,
  CUSTOM_ELEMENTS_SCHEMA,
  inject,
  Input,
  NgZone,
  OnDestroy,
  OnInit,
  ViewEncapsulation,
} from '@angular/core';
import { EntandoConfig } from './types/config';
import { ExpenseService } from './services/expense.service';
import { Expense } from './types/expense';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { filter, switchMap, take } from 'rxjs';
import { KeycloakService } from './services/keycloak.service';
import { mediatorInstance } from '@entando/mfecommunication';

@Component({
  selector: 'expense-list',
  imports: [CurrencyPipe, DatePipe],
  templateUrl: './app.component.html',
  styleUrls: ['../styles.css', './app.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent implements OnInit, OnDestroy {
  @Input() config!: string;
  public parsedConfig!: EntandoConfig;

  private expenseService = inject(ExpenseService);
  private keycloakService = inject(KeycloakService);
  private ngZone = inject(NgZone)

  public expenses: Expense[] = [];

  constructor() {
    mediatorInstance.subscribe('update-expenses-table', {
      callerId: 'updateExpensesTable',
      callback: () => this.ngZone.run(() => this.getExpenses()), // EntandoTips
    });
  }



  ngOnInit(): void {
    this.parsedConfig = JSON.parse(this.config) as EntandoConfig;
    this.expenseService.baseUrl =
      this.parsedConfig.systemParams.api['expense-api'].url;
    this.getExpenses();
  }

  ngOnDestroy(): void {
    mediatorInstance.unsubscribe('update-expenses-table', 'updateExpensesTable');
  }

  private getExpenses() {
    this.keycloakService.instance$
      .pipe(
        filter((instance) => instance.initialized),
        take(1),
        switchMap(() => this.expenseService.getAllExpenses())
      )
      .subscribe((data) => {
        this.expenses = data;
      });
  }

  public approve(id: string): void {
    this.expenseService
      .updateStatus(id, 'APPROVED')
      .pipe(switchMap(() => this.expenseService.getAllExpenses()))
      .subscribe((data) => {
        this.expenses = data;
      });
  }

  public deny(id: string): void {
    this.expenseService
      .updateStatus(id, 'DENIED')
      .pipe(switchMap(() => this.expenseService.getAllExpenses()))
      .subscribe((data) => {
        this.expenses = data;
      });
  }
}
