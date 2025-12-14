// src/app/services/admin.spec.ts
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AdminService, Admin } from './admin';

describe('AdminService', () => {
  let service: AdminService;
  let httpMock: HttpTestingController;

  const dummyAdmin: Admin = {
    id: 1,
    username: 'adminuser',
    password: 'Admin@1234'
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AdminService]
    });

    service = TestBed.inject(AdminService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // ensure no outstanding requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should perform login and return Admin object', () => {
    service.login({ username: 'adminuser', password: 'Admin@1234' }).subscribe((admin) => {
      expect(admin).toEqual(dummyAdmin);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/admins/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ username: 'adminuser', password: 'Admin@1234' });

    req.flush(dummyAdmin);
  });

  it('should get list of admins', () => {
    const dummyAdmins: Admin[] = [
      { id: 1, username: 'adminuser1', password: 'Admin@1234' },
      { id: 2, username: 'adminuser2', password: 'Admin@5678' },
    ];

    service.getAdmins().subscribe((admins) => {
      expect(admins.length).toBe(2);
      expect(admins).toEqual(dummyAdmins);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/admins');
    expect(req.request.method).toBe('GET');
    req.flush(dummyAdmins);
  });
});
