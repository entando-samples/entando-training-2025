import {
  Component,
  CUSTOM_ELEMENTS_SCHEMA,
  Input,
  OnDestroy,
  OnInit,
  ViewEncapsulation,
} from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'expense-list-config',
  imports: [ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['../styles.css', './app.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class AppComponent implements OnInit, OnDestroy {
  _config!: { role: string };

  @Input() set config(value: { role: string } | string) {
    if (typeof value === 'string') {
      this._config = JSON.parse(value);
    } else {
      this._config = value;
    }
    this.configForm.patchValue({
      role: this._config.role,
    });
  }

  get config(): { role: string } {
    return this._config;
  }

  private subscription!: Subscription;

  title = 'expense-list-config';
  configForm = new FormGroup({
    role: new FormControl('', [Validators.required]),
  });

  ngOnInit(): void {
    this.subscription = this.configForm
      .get('role')
      ?.valueChanges.subscribe((value) => {
        this.config = { role: value as string };
      }) as Subscription;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
