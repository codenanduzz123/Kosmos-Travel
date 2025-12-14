import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  username = '';
  fullName = '';
  phoneNumber = '';
  password = '';

  register() {
    if (this.username && this.fullName && this.phoneNumber && this.password) {
      alert(`Registered ${this.fullName} (demo).`);
      this.username = this.fullName = this.phoneNumber = this.password = '';
    } else {
      alert('Please fill all fields.');
    }
  }
}
