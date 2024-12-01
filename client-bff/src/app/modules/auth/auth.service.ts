import { Injectable } from '@nestjs/common';
import { UserRequestDto } from 'src/app/commons/dto/request/user-request.dto';
import { UserResponseDto } from 'src/app/commons/dto/response/user-response.dto';
import { AuthLoginDto } from './dto/auth-login.dto';
import { AuthLoginRepsonseDto } from './dto/auth-token.dto';
import { HttpRequestService } from 'src/app/commons/modules/http/http-request.service';
import { AppConfigService } from 'src/app/config/config.service';
import { TokenStorageService } from 'src/app/commons/modules/token/token-storage.service';

@Injectable()
export class AuthService {
  constructor(
    private readonly httpRequestService: HttpRequestService,
    private readonly appConfigService: AppConfigService,
    private readonly tokenStorageService: TokenStorageService
  ) {}

  async create(user: UserRequestDto): Promise<UserResponseDto> {
    return await this.httpRequestService.request(
      'POST',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/auth/register`,
      user,
    );
  }

  async login(login: AuthLoginDto): Promise<AuthLoginRepsonseDto> {
    const response = await this.httpRequestService.request(
      'POST',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/auth/login`,
      login,
    );
    await this.tokenStorageService.setAccessToken(response.access_token);
    return response;
  }
}
