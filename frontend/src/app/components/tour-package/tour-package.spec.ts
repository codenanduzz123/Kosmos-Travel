import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TourPackageComponent } from './tour-package';

describe('TourPackageComponent', () => {
  let component: TourPackageComponent;
  let fixture: ComponentFixture<TourPackageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TourPackageComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(TourPackageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the tour package component', () => {
    expect(component).toBeTruthy();
  });

  it('should have 4 tour packages', () => {
    expect(component.packages.length).toBe(4);
  });

  it('should contain a Kerala package', () => {
    expect(component.packages.some(p => p.state === 'Kerala')).toBeTrue();
  });
});
