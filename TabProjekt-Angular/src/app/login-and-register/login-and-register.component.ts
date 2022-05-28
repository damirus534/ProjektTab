import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../core/user/user';
import { UserService } from '../core/user/user.service';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../core/website-service/cart/cart.service';

@Component({
  selector: 'login-and-register',
  templateUrl: './login-and-register.component.html',
  styleUrls: ['./login-and-register.component.css']
})
export class LoginAndRegisterComponent implements OnInit {

  // Fields and flags for logging in.
  loginUser: User = new User();
  badCredentials: boolean = false;
  isSavingAnonCart: boolean = false;
  rowHeight: string = '93vh';

  // Fields and flags for registration.
  registerUser: User = new User();
  userAlreadyExists: boolean = false;
  accountCreated: boolean = false;

  // Login form for validation.
  loginForm = new FormGroup({
    loginEmail: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+[.][a-z]{2,4}$")]),
    loginPassword: new FormControl('', [Validators.required])
  });

  // Register form for valdation.
  registerForm = new FormGroup({
    registerEmail: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+[.][a-z]{2,4}$")]),
    registerPassword: new FormControl('', [Validators.required]),
    registerAddress: new FormControl('', [Validators.required])
  });

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private cartService: CartService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.isSavingAnonCart = this.cartService.saveAnonCart && this.cartService.saveAnonCartVerificationId === this.activatedRoute.snapshot.queryParamMap.get('_replaceId');
    this.rowHeight = this.isSavingAnonCart ? '88vh' : '93vh';
  }

  // Method which verifies correctness of input data - when it does not match, info is displayed.
  onLoginSubmit() {
    if (this.isSavingAnonCart) {
      this.authService.login(this.loginEmail?.value, this.loginPassword?.value).subscribe(() => {
        this.authService.isLoggedIn$.subscribe((isLoggedIn) => {
          this.badCredentials = !isLoggedIn;
          if (isLoggedIn) {
            if (this.authService.userToken.role === 'admin') {
              this.router.navigate(['/admin']);
            } else if (this.authService.userToken.role === 'user') {
              for (let cartElement of this.cartService.actualCartList) {
                this.cartService.addToCart(this.authService.userToken.id, cartElement.product.id, cartElement.amount).subscribe();
              }
              this.router.navigate(['']);
              this.cartService.actualCartList = [];
              this.cartService.saveAnonCart = false;
              this.cartService.saveAnonCartVerificationId = null;
            }
          }
        });
      });
    } else {
      this.doLogin(this.loginEmail?.value, this.loginPassword?.value);
    }
  }

  private doLogin(email: string, password: string) {
    this.authService.login(email, password).subscribe(() => {
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
    this.userService.isUserExists(this.registerUser, this.registerEmail?.value, this.registerPassword?.value, this.registerAddress?.value).subscribe((userExists) => {
      if (!userExists) {
        this.accountCreated = true;
        this.userService.saveUser(this.registerUser).subscribe((savedUser) => {
          if (this.isSavingAnonCart) {
            for (let cartElement of this.cartService.actualCartList) {
              this.cartService.addToCart(savedUser.id, cartElement.product.id, cartElement.amount).subscribe();
            }
            this.cartService.actualCartList = [];
            this.cartService.saveAnonCart = false;
            this.cartService.saveAnonCartVerificationId = null;
          }
          this.doLogin(this.registerEmail?.value, this.registerPassword?.value);
        });
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
