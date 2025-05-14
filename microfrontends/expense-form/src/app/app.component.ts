import { Component, CUSTOM_ELEMENTS_SCHEMA, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'expense-form',
  imports: [ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['../styles.css','./app.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent {
  expenseForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    expense: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required]),
  })


  onSubmit() { 
    console.log(this.expenseForm.value)
  }
  
}
