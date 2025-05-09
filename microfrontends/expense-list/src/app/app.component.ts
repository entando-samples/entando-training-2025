import {
  Component,
  CUSTOM_ELEMENTS_SCHEMA,
  inject,
  Input,
  OnInit,
  ViewEncapsulation,
} from '@angular/core';
import { EntandoConfig } from './types/config';
import { ExpenseService } from './services/expense.service';
import { Expense } from './types/expense';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { filter, switchMap, take } from 'rxjs';
import { KeycloakService } from './services/keycloak.service';
@Component({
  selector: 'expense-list',
  imports: [CurrencyPipe, DatePipe],
  templateUrl: './app.component.html',
  styleUrls: ['../styles.css', './app.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent implements OnInit {
  @Input() config!: string;
  public parsedConfig!: EntandoConfig;

  private expenseService = inject(ExpenseService);
  private keycloakService = inject(KeycloakService);

  public expenses: Expense[] = [];

  ngOnInit(): void {
    this.parsedConfig = JSON.parse(this.config) as EntandoConfig;
    this.expenseService.baseUrl =
      this.parsedConfig.systemParams.api['expense-api'].url;

    this.keycloakService.instance$
      .pipe(
        filter((instance) => instance.initialized),
        take(1),
        switchMap(() => this.expenseService.getAllExpenses())
      )
      .subscribe((data) => {
        this.expenses = data;
        console.log('Expenses:', this.expenses);
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
