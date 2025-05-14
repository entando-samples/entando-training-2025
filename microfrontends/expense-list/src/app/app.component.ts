import { Component, CUSTOM_ELEMENTS_SCHEMA, ViewEncapsulation } from '@angular/core';
import { Expense } from './types/expense';
import { CurrencyPipe, DatePipe } from '@angular/common';
@Component({
  selector: 'expense-list',
  imports: [CurrencyPipe, DatePipe],
  templateUrl: './app.component.html',
  styleUrls: ['../styles.css', './app.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent {
  expenses: Expense[] = [];
}
