import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { UpdateUserDto } from './dto/request/update-user.dto';
import { UserRequestDto } from './dto/request/user-request.dto';
import { HttpService } from '@nestjs/axios';
import { UserResponseDto } from './dto/response/user-response.dto';
import { BASE_URLS } from 'src/app/config/service.url';
import { Observable } from 'rxjs';
import { AxiosResponse } from 'axios';

@Injectable()
export class UsersService {
  constructor(private readonly httpService: HttpService) {}

  async create(user: UserRequestDto): Promise<any> {
    console.log('Dados de usuário:', JSON.stringify(user, null, 2));
    try {
      const response = await this.httpService
        .post(`${BASE_URLS.USERS_SERVICE}/auth/register`, user)
        .toPromise();
      return response.data;

    } catch (error) {
      throw new HttpException(
        error.response ? error.response.data : 'Erro ao cadastrar usuário',
        error.response ? error.response.status : HttpStatus.BAD_REQUEST,
      );
    }
  }

  findAll() {
    return "";
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
