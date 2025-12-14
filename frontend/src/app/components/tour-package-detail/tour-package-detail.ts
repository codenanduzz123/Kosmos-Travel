import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tour-package-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tour-package-detail.html',
  styleUrls: ['./tour-package-detail.css']
})
export class TourPackageDetailComponent implements OnInit {
  packageId!: string | null;
  tourPackage: any;

  tourPackages = [
    {
      id: '1',
      name: 'Kerala Backwater Bliss',
      state: 'Kerala',
      description: `Experience serene houseboat stays, tranquil backwaters, Ayurvedic spas, and vibrant village life. Enjoy the picturesque landscapes and relax amidst nature's best.`,
      price: 12000,
      duration: '3 nights',
      itinerary: [   // changed 'plan' to 'itinerary'
        'Day 1: Arrival and houseboat stay along the backwaters.',
        'Day 2: Visit to local villages and Ayurvedic spa experience.',
        'Day 3: Leisure boat cruise and departure.'
      ]
    },
    {
      id: '2',
      name: 'Tamil Nadu Heritage Trail',
      state: 'Tamil Nadu',
      description: `Explore magnificent temple architecture, experience classical dance performances, and enjoy the beautiful coastal scenery of Tamil Nadu.`,
      price: 11000,
      duration: '3 nights',
      itinerary: [
        'Day 1: Temple visits and cultural show.',
        'Day 2: Explore local markets and beach.',
        'Day 3: Heritage palace tour and departure.'
      ]
    },
    {
      id: '3',
      name: 'Andhra Coastal & Temple Tour',
      state: 'Andhra Pradesh',
      description: `Discover pristine beaches, hill valleys, and historic temples in Andhra Pradesh on this exciting coastal and cultural journey.`,
      price: 9500,
      duration: '2 nights',
      itinerary: [
        'Day 1: Beach visit and temple tour.',
        'Day 2: Hill valley exploration and departure.'
      ]
    },
    {
      id: '4',
      name: 'Karnataka Hill & Heritage Escape',
      state: 'Karnataka',
      description: `Visit coffee plantations, historic heritage palaces, and scenic hill stations in Karnataka for a perfect mix of nature and culture.`,
      price: 11500,
      duration: '3 nights',
      itinerary: [
        'Day 1: Coffee plantation tour and local sightseeing.',
        'Day 2: Heritage palace visits.',
        'Day 3: Hill station exploration and departure.'
      ]
    }
  ];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.packageId = this.route.snapshot.paramMap.get('id');
    this.tourPackage = this.tourPackages.find(p => p.id === this.packageId);
  }
}
