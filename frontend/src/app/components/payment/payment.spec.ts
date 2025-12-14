import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { PaymentComponent } from './payment';

describe('PaymentComponent', () => {
  let component: PaymentComponent;
  let fixture: ComponentFixture<PaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaymentComponent, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(PaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create payment component', () => {
    expect(component).toBeTruthy();
  });

  it('should have empty fields initially', () => {
    expect(component.cardNumber).toBe('');
    expect(component.cardHolder).toBe('');
    expect(component.expiry).toBe('');
    expect(component.cvv).toBe('');
  });

  it('should validate CVV correctly', () => {
    component.cvv = '123';
    expect(component.isCvvValid()).toBeTrue();

    component.cvv = '12a';
    expect(component.isCvvValid()).toBeFalse();

    component.cvv = '12345';
    expect(component.isCvvValid()).toBeFalse();

    component.cvv = '1234';
    expect(component.isCvvValid()).toBeTrue();
  });
});
