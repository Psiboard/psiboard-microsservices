import { Injectable } from '@nestjs/common';
import { UserRequestDto } from 'src/app/commons/dto/request/user-request.dto';
import { UserResponseDto } from 'src/app/commons/dto/response/user-response.dto';
import { BASE_URLS } from 'src/app/config/service.url';
import { AuthLoginDto } from './dto/auth-login.dto';
import { AuthTokenDto } from './dto/auth-token.dto';
import { HttpRequestService } from 'src/app/commons/http/http-request.service';

@Injectable()
export class AuthService {
  constructor(private readonly httpRequestService: HttpRequestService) {}

  async create(user: UserRequestDto): Promise<UserResponseDto> {
    return await this.httpRequestService.request(
      'POST',
      `${BASE_URLS.USERS_SERVICE}/auth/register`,
      user,
    );
  }

  async login(login: AuthLoginDto): Promise<AuthTokenDto> {
    return await this.httpRequestService.request(
      'POST',
      `${BASE_URLS.USERS_SERVICE}/auth/login`,
      login,
    );
  }
}
