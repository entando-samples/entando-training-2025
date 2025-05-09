export type ExpenseStatus = 'DRAFT' | 'APPROVED' | 'DENIED';

export interface Expense {
    id?: string;
    status?: ExpenseStatus;
    creaedAt?: string;
    title: string;
    expense: number;
    date: string;
}