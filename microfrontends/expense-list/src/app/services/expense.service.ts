import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Expense, ExpenseStatus } from '../types/expense';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
  public baseUrl: string = '';

  private http = inject(HttpClient);
  constructor() { }

  getAllExpenses(): Observable<Expense[]> {
    return this.http.get<Expense[]>(`${this.baseUrl}/api/expenses`)
  }

  updateStatus(id: string, status: ExpenseStatus): Observable<Expense> {
    return this.http.patch<Expense>(`${this.baseUrl}/api/expenses/${id}/${status.toUpperCase()}`, { });
  }
}
