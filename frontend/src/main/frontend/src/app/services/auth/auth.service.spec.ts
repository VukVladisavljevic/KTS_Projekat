import { TestBed, inject } from '@angular/core/testing';

import { AuthService } from './auth.service';
import { JwtService } from './jwt.service';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';

describe('AuthService', () => {
  let authService: AuthService;
  let jwtService: JwtService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ AuthService, JwtService ]
    });

    authService = TestBed.get(AuthService);
    jwtService = TestBed.get(JwtService);
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    const service: AuthService = TestBed.get(AuthService);
    expect(service).toBeTruthy();
  });

  it('should create jwt service', inject([JwtService], (service: JwtService) => {
    expect(service).toBeTruthy();
  }));

  it('should return flag is login successfully done', () => {
    const credentials = {
      'username': 'aa',
      'password': 'aa'
    };

    const response = {
      'value': 'some.token.value'
    };

    authService.login(credentials)
      .subscribe((successfullyLoggedIn: boolean) => {
        expect(successfullyLoggedIn).toEqual(true);
      });
    const httpRequest = httpMock.expectOne('/api/login');
    expect(httpRequest.request.method).toEqual('POST');
    httpRequest.flush(response);
  });

  it('should return flag if given username is available', () => {
     authService.checkUsername('noviUsername')
       .subscribe((usernameAvailable: boolean) => {
         expect(usernameAvailable).toEqual(true);
       });
     const httpRequest = httpMock.expectOne('/api/check_username?username=noviUsername');
     expect(httpRequest.request.method).toEqual('GET');
     expect(httpRequest.request.params.get('username')).toEqual('noviUsername');
     httpRequest.flush(true);
   });

  it('should return current user', () => {
    const credentials = {
      'username': 'aa',
      'password': 'aa'
    };

    authService.login(credentials).subscribe();

    const response = {
      'id': 789,
      'firstName': 'aa',
      'lastName': 'aa',
      'email': 'kwhshocker@gmail.com'
    };

    authService.currentUser()
      .subscribe((user: Account) => {
        expect(user).toContain(response);
      });
    const httpRequest = httpMock.expectOne('/api/current_user');
    expect(httpRequest.request.method).toEqual('GET');
    httpRequest.flush(response);
  });
});
