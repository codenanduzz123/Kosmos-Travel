import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TourPackageDetailComponent } from './tour-package-detail';

describe('TourPackageDetailComponent', () => {
  let component: TourPackageDetailComponent;
  let fixture: ComponentFixture<TourPackageDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TourPackageDetailComponent, RouterTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(TourPackageDetailComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should find the correct tour package by id', () => {
    component.packageId = '1';
    component.ngOnInit();
    expect(component.tourPackage).toBeDefined();
    expect(component.tourPackage?.name).toBe('Kerala Backwater Bliss');
  });

  it('should set tourPackage to undefined for invalid id', () => {
    component.packageId = '999'; // Non-existent ID
    component.ngOnInit();
    expect(component.tourPackage).toBeUndefined();
  });

  it('should display package details in the template', () => {
    component.packageId = '2';
    component.ngOnInit();
    fixture.detectChanges();

    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Tamil Nadu Heritage Trail');
    expect(compiled.querySelector('p')?.textContent).toContain('Explore magnificent temple architecture');
    expect(compiled.querySelector('ul li')).toBeTruthy(); // itinerary list item exists
  });
});
