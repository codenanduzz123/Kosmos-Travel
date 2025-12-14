import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  username = '';
  password = '';

  login() {
    if (!this.username || !this.password) {
      alert('Please fill username and password.');
      return;
    }
    // TODO: replace with real auth call
    alert(`Demo login: ${this.username}`);
    this.username = this.password = '';
  }
}
