import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../core/user/user';
import { UserService } from '../core/user/user.service';

@Component({
  selector: 'login-and-register',
  templateUrl: './login-and-register.component.html',
  styleUrls: ['./login-and-register.component.css']
})
export class LoginAndRegisterComponent implements OnInit {

  // Fields and flags for logging in.
  public loginUser: User = new User();
  public badCredentials: Boolean = false;

  // Fields and flags for registration.
  public registerUser: User = new User();
  public userAlreadyExists: Boolean = false;
  public accountCreated: Boolean = false;

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

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    
  }

  // Method which verifies correctness of input data - when it does not match, info is displayed.
  // TODO: logging in and rerouting to main page with offers
  public onLoginSubmit() {
    this.userService.verify(this.loginUser, this.loginForm).subscribe({
      next: (verified) => {
        console.log(verified);
        this.badCredentials = !verified;
      },
      error: (e) => console.error
    });
  }

  // Method which creates new user in database if they do not exist already. If they do, info is displayed.
  public onRegisterSubmit() {
    this.userService.isUserExists(this.registerUser, this.registerForm).subscribe({
      next: (isUserExists) => {
        this.userAlreadyExists = isUserExists;
      },
      error: console.error,
      complete: () => {
        if(this.userAlreadyExists) {
          this.accountCreated = false;
        } 
        else {
          this.userService.saveUser(this.registerUser).subscribe({
            error: console.error,
            complete: () => {
              console.log(this.registerUser);
              this.accountCreated = true;
            }
          });
        }
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
