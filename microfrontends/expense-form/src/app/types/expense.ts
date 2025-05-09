export type ExpenseStatus = 'draft' | 'approved' | 'denied';

export interface Expense {
    id?: string;
    status?: ExpenseStatus;
    creaedAt?: string;
    title: string;
    expense: number;
    date: string;
}