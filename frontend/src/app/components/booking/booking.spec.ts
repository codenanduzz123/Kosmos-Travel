import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BookingComponent } from './booking';
import { FormsModule } from '@angular/forms';

describe('BookingComponent', () => {
  let component: BookingComponent;
  let fixture: ComponentFixture<BookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookingComponent, FormsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(BookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create booking component', () => {
    expect(component).toBeTruthy();
  });

  it('should have default packages', () => {
    expect(component.packages.length).toBeGreaterThan(0);
  });
});
