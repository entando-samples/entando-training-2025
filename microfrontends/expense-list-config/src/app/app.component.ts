import { Component, CUSTOM_ELEMENTS_SCHEMA, Input, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'expense-list-config',
  imports: [ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['../styles.css','./app.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent implements OnInit, OnDestroy {

  @Input() config!: { role: string } | string;

  private subscription!: Subscription;


  title = 'expense-list-config';
  configForm = new FormGroup({
    role: new FormControl('', [Validators.required])
  })


  ngOnInit(): void {
    if (typeof this.config === 'string') this.config = JSON.parse(this.config);

    this.subscription = this.configForm.get('role')?.valueChanges.subscribe((value) => {
      this.config = { role: value as string};
      console.log('Config changed:', this.config);
    }) as Subscription;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  

}
