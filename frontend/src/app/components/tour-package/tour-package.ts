import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

interface TourPackage {
  id: number;
  title: string;
  state: string;
  description: string;
  price: number;
  duration: string;
  image: string;
}

@Component({
  selector: 'app-tour-package',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './tour-package.html',
  styleUrls: ['./tour-package.css']
})
export class TourPackageComponent {
  packages: TourPackage[] = [
    { id: 1, title: 'Kerala Backwater Bliss', state: 'Kerala', description: 'Houseboat stay, backwaters, Ayurvedic spa and village life.', price: 12000, duration: '3 nights', image: '/assets/images/culture-kerala.jpg' },
    { id: 2, title: 'Tamil Nadu Heritage Trail', state: 'Tamil Nadu', description: 'Temple architecture, classical dance and coastal getaways.', price: 11000, duration: '3 nights', image: '/assets/images/culture-tamilnadu.jpg' },
    { id: 3, title: 'Andhra Coastal & Temple Tour', state: 'Andhra Pradesh', description: 'Beaches, hill valley visits and historic temples.', price: 9500, duration: '2 nights', image: '/assets/images/culture-ap.jpg' },
    { id: 4, title: 'Karnataka Hill & Heritage Escape', state: 'Karnataka', description: 'Coffee plantations, heritage palaces and scenic hills.', price: 11500, duration: '3 nights', image: '/assets/images/culture-karnataka.jpg' }
  ];
}
