// src/app/services/booking.spec.ts
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { BookingService, Booking } from './booking';

describe('BookingService', () => {
  let service: BookingService;
  let httpMock: HttpTestingController;

  const dummyBooking: Booking = {
    id: 1,
    userId: 1,
    packageId: 1,
    bookingDate: new Date(),
    status: 'CONFIRMED'
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BookingService]
    });
    service = TestBed.inject(BookingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should create a booking', () => {
    service.createBooking(dummyBooking).subscribe(booking => {
      expect(booking).toEqual(dummyBooking);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/bookings');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(dummyBooking);
    req.flush(dummyBooking);
  });
});

