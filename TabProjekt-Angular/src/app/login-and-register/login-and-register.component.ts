import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../core/user/user';
import { UserService } from '../core/user/user.service';
import { AuthService } from '../services/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';

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

  constructor(private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    
  }

  // Method which verifies correctness of input data - when it does not match, info is displayed.
  // TODO: logging in and rerouting to main page with offers
  public onLoginSubmit() {
    this.doLogin();
  }

  private doLogin() {
    this.authService.login(this.loginEmail?.value, this.loginPassword?.value).subscribe((token) => {
      if(token != "") {
        localStorage.setItem("JWT_TOKEN", token);
      }
    });
  }

  // Method which creates new user in database if they do not exist already. If they do, info is displayed.
  public onRegisterSubmit() {
    this.userService.isUserExists(this.registerUser, this.registerEmail?.value, this.registerPassword?.value, this.registerAddress?.value).subscribe((result) => {
      this.userAlreadyExists = result;
      if(!this.userAlreadyExists) {
        this.userService.saveUser(this.registerUser).subscribe({
          next: () => this.accountCreated = true,
          error: console.error
        });
      } else {
        this.accountCreated = false;
      }
    });
  }

  public checkToken() {
    const helper = new JwtHelperService();
    const token = localStorage.getItem('JWT_TOKEN');
    if(token !== null) {
      const role = helper.decodeToken(token).role;
      console.log(role);
    } else {
      console.log('DUPSKO');
    }
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
