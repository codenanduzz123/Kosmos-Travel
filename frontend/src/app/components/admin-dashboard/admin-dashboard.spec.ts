import { TestBed } from '@angular/core/testing';
import { AdminDashboardComponent } from './admin-dashboard';

describe('AdminDashboardComponent', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [AdminDashboardComponent]
  }));

  it('should create', () => {
    const fixture = TestBed.createComponent(AdminDashboardComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });
});
