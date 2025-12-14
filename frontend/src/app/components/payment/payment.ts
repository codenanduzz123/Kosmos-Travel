import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './payment.html',
  styleUrls: ['./payment.css'],
})
export class PaymentComponent {
  cardNumber = '';
  cardHolder = '';
  expiry = '';
  cvv = '';

  isCvvValid(): boolean {
    const cvvRegex = /^[0-9]{3,4}$/;
    return cvvRegex.test(this.cvv);
  }

  processPayment(form: NgForm): void {
    if (form.valid && this.isCvvValid()) {
      alert('Payment processed (demo). Thank you!');
      this.cardNumber = '';
      this.cardHolder = '';
      this.expiry = '';
      this.cvv = '';
      form.resetForm();
    } else {
      alert('Please fill all payment details correctly, including a valid CVV.');
    }
  }
}
