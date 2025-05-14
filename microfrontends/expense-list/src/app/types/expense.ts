export type ExpenseStatus = 'APPROVED' | 'DENIED' | 'DRAFT';

export interface Expense {
    id: string;
    title: string;
    expense: number;
    date: string;
    status: ExpenseStatus;
}