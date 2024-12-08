import { Injectable } from '@nestjs/common';
import { UpdateUserDto } from '../../commons/dto/request/update-user.dto';
import { HttpRequestService } from 'src/app/commons/modules/http/http-request.service';
import { AppConfigService } from 'src/app/config/config.service';

@Injectable()
export class UsersService {
  constructor(
    private readonly httpRequestService: HttpRequestService,
    private readonly appConfigService: AppConfigService,
  ) {}
  async findByEmail(email: string) {
    return await this.httpRequestService.request(
      'GET',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/users/user/${email}`,
    );
  }
  async findAll() {
    return await this.httpRequestService.request(
      'GET',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/users`,
    );
  }

  async findOne(id: string) {
    return await this.httpRequestService.request(
      'GET',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/users/${id}`,
    );
  }

  async update(id: string, updateUserDto: UpdateUserDto) {
    return await this.httpRequestService.request(
      'PUT',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/users/${id}`,
      updateUserDto,
    );
  }

  async remove(id: string) {
    return await this.httpRequestService.request(
      'DELETE',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/users/${id}`,
    );
  }
}
