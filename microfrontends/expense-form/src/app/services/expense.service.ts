import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Expense } from '../types/expense';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
  public baseUrl: string = '';

  private http = inject(HttpClient);
  constructor() { }

  addNewExpense(data: Expense): Observable<Expense> {
    return this.http.post<Expense>(`${this.baseUrl}/api/expenses`, data)
  }

}
