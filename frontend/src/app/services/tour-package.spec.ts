// src/app/services/tour-package.spec.ts
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TourPackageService, TourPackage } from './tour-package';

describe('TourPackageService', () => {
  let service: TourPackageService;
  let httpMock: HttpTestingController;

  const dummyPackages: TourPackage[] = [
    {
      id: 1,
      title: 'Kerala Backwater Bliss',
      state: 'Kerala',
      description: 'Houseboat stay, backwaters, Ayurvedic spa and village life.',
      price: 12000,
      duration: '3 nights',
      image: '/assets/images/culture-kerala.jpg'
    }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TourPackageService]
    });
    service = TestBed.inject(TourPackageService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get tour packages', () => {
    service.getPackages().subscribe(packages => {
      expect(packages.length).toBeGreaterThan(0);
      expect(packages).toEqual(dummyPackages);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/tour-packages');
    expect(req.request.method).toBe('GET');
    req.flush(dummyPackages);
  });
});
