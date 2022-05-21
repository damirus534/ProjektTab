import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../core/user/user';
import { UserService } from '../core/user/user.service';
import { AuthService } from '../services/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Component({
  selector: 'login-and-register',
  templateUrl: './login-and-register.component.html',
  styleUrls: ['./login-and-register.component.css']
})
export class LoginAndRegisterComponent implements OnInit {

  // Fields and flags for logging in.
  loginUser: User = new User();
  badCredentials: boolean = false;

  // Fields and flags for registration.
  registerUser: User = new User();
  userAlreadyExists: boolean = false;
  accountCreated: boolean = false;

  // Login form for validation.
  loginForm = new FormGroup({
    loginEmail: new FormControl('', [Validators.required, Validators.email]),
    loginPassword: new FormControl('', [Validators.required])
  });

  // Register form for valdation.
  registerForm = new FormGroup({
    registerEmail: new FormControl('', [Validators.required, Validators.email]),
    registerPassword: new FormControl('', [Validators.required]),
    registerAddress: new FormControl('', [Validators.required])
  });

  constructor(private userService: UserService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {

  }

  // Method which verifies correctness of input data - when it does not match, info is displayed.
  onLoginSubmit() {
    this.authService.login(this.loginEmail?.value, this.loginPassword?.value).subscribe(() => {
      this.authService.isLoggedIn$.subscribe((isLoggedIn) => {
        this.badCredentials = !isLoggedIn;
        if(isLoggedIn) {
          if(this.authService.userToken.role === 'admin') {
            this.router.navigate(['/admin']);
          } else if (this.authService.userToken.role === 'user') {
            this.router.navigate(['']);
          }
        }
      });
    });
  }

  // Method which creates new user in database if they do not exist already. If they do, info is displayed.
  onRegisterSubmit() {
    this.userService.isUserExists(this.registerUser, this.registerEmail?.value, this.registerPassword?.value, this.registerAddress?.value).subscribe((result) => {
      this.userAlreadyExists = result;
      if(!this.userAlreadyExists) {
        this.userService.saveUser(this.registerUser).subscribe();
        this.accountCreated = true
      } else {
        this.accountCreated = false;
      }
    });
  }

  // Getters for FormGroups and validation checking - cleaner code in html template with *ngIf.
  get loginEmail() {
    return this.loginForm.get('loginEmail');
  }

  get loginPassword() {
    return this.loginForm.get('loginPassword');
  }

  get registerEmail() {
    return this.registerForm.get('registerEmail');
  }

  get registerPassword() {
    return this.registerForm.get('registerPassword');
  }

  get registerAddress() {
    return this.registerForm.get('registerAddress');
  }

}
