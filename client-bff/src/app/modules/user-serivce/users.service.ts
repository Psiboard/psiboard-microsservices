import { Injectable } from '@nestjs/common';
import { UpdateUserDto } from '../../commons/dto/request/update-user.dto';
import { HttpRequestService } from 'src/app/commons/http/http-request.service';
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

  findOne(id: number) {
    return `This action returns a #${id} user`;
  }

  update(id: number, updateUserDto: UpdateUserDto) {
    return `This action updates a #${id} user`;
  }

  remove(id: number) {
    return `This action removes a #${id} user`;
  }
}
