import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './booking.html',
  styleUrls: ['./booking.css']
})
export class BookingComponent {
  name = '';
  email = '';
  packageSelected = '';
  date = '';

  packages = [
    'Kerala Backwater Bliss',
    'Tamil Nadu Heritage Trail',
    'Andhra Coastal & Temple Tour',
    'Karnataka Hill & Heritage Escape'
  ];

  onSubmit() {
    if (this.name && this.email && this.packageSelected && this.date) {
      // demo behaviour: clear fields & show success
      alert(`Thank you ${this.name}! Your booking for "${this.packageSelected}" on ${this.date} is received.`);
      this.name = this.email = this.packageSelected = this.date = '';
    } else {
      alert('Please complete all fields before submitting.');
    }
  }
}
