import { Routes } from '@angular/router';

import { WelcomeComponent } from './components/welcome/welcome';
import { LoginComponent } from './components/login/login';
import { RegisterComponent } from './components/register/register';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard';
import { BookingComponent } from './components/booking/booking';
import { PaymentComponent } from './components/payment/payment';
import { TourPackageComponent } from './components/tour-package/tour-package';
import { TourPackageDetailComponent } from './components/tour-package-detail/tour-package-detail';

export const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'booking', component: BookingComponent },
  { path: 'payment', component: PaymentComponent },
  { path: 'tour-package', component: TourPackageComponent },
  { path: 'tour-package/:id', component: TourPackageDetailComponent },  // Matches the Explore button route
  { path: '**', redirectTo: '/welcome' }
];
