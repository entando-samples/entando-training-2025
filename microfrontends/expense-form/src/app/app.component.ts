import {
  Component,
  CUSTOM_ELEMENTS_SCHEMA,
  inject,
  ViewEncapsulation,
  OnInit,
  Input,
} from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ExpenseService } from './services/expense.service';
import { Expense } from './types/expense';
import { EntandoConfig } from './types/config';
import { CommonModule } from '@angular/common';

interface ExpenseForm {
  title: FormControl<string>;
  expense: FormControl<number>;
  date: FormControl<string>;
}

@Component({
  selector: 'expense-form',
  imports: [ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['../styles.css','./app.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent implements OnInit {
  @Input() config!: string;
  public parsedConfig!: EntandoConfig;
  private expenseService = inject(ExpenseService);

  expenseForm: FormGroup<ExpenseForm> = new FormGroup<ExpenseForm>({
    title: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
    expense: new FormControl<number>(0, {
      nonNullable: true,
      validators: [Validators.required],
    }),
    date: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
  });

  ngOnInit(): void {
    console.log('Config received:', this.config);
    this.parsedConfig = JSON.parse(this.config) as EntandoConfig;
    console.log('Parsed config:', this.parsedConfig);
    this.expenseService.baseUrl =
      this.parsedConfig.systemParams.api['expense-api'].url;

  }

  onSubmit() {
    const formValues = this.expenseForm.getRawValue();
    const data: Expense = {
      ...formValues,
      date: new Date(formValues.date).toISOString(),
    };

    this.expenseService.addNewExpense(data).subscribe((res) => {
      console.log('Expense created successfully', res);
    });
  }
}
