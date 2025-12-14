// src/app/services/payment.spec.ts
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { PaymentService, PaymentRequest, Payment } from './payment';

describe('PaymentService', () => {
  let service: PaymentService;
  let httpMock: HttpTestingController;

  const dummyPaymentRequest: PaymentRequest = {
    userId: 1,
    amount: 5000,
    cardNumber: '4111111111111111',
    expiry: '12/25',
    cvv: '123'
  };

  const dummyPayment: Payment = {
    id: 1,
    userId: 1,
    amount: 5000,
    status: 'SUCCESS',
    paymentDate: new Date()
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PaymentService]
    });
    service = TestBed.inject(PaymentService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should process payment', () => {
    service.processPayment(dummyPaymentRequest).subscribe(payment => {
      expect(payment).toEqual(dummyPayment);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/payments/process');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(dummyPaymentRequest);
    req.flush(dummyPayment);
  });
});

