import { TestBed } from '@angular/core/testing';
import { UserDashboardComponent } from './user-dashboard';

describe('UserDashboardComponent', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [UserDashboardComponent]
  }));

  it('should create the component', () => {
    const fixture = TestBed.createComponent(UserDashboardComponent);
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
