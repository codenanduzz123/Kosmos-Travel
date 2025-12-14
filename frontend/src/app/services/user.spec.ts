// src/app/services/user.spec.ts
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { UserService, User } from './user';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  const dummyUser: User = {
    id: 1,
    username: 'user1',
    fullName: 'Test User',
    password: 'User@1234',
    phoneNumber: '9876543210'
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should register a user', () => {
    service.registerUser(dummyUser).subscribe(user => {
      expect(user).toEqual(dummyUser);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/users/register');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(dummyUser);
    req.flush(dummyUser);
  });

  it('should fetch all users', () => {
    const dummyUsers: User[] = [dummyUser];
    service.getUsers().subscribe(users => {
      expect(users.length).toBe(1);
      expect(users).toEqual(dummyUsers);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/users');
    expect(req.request.method).toBe('GET');
    req.flush(dummyUsers);
  });
});
