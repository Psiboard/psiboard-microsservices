import { Injectable } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';

@Injectable()
export class AppConfigService {
  constructor(private configService: ConfigService) {}

  get baseUrls() {
    return {
      USERS_SERVICE: `${this.configService.get<string>('API_GATEWAY_URL')}/users-service`,
      PATIENTS_SERVICE: `${this.configService.get<string>('API_GATEWAY_URL')}/patients-service`,
    };
  }
}