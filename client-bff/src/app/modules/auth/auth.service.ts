import { HttpService } from '@nestjs/axios';
import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { firstValueFrom } from 'rxjs';
import { UpdateUserDto } from 'src/app/commons/dto/request/update-user.dto';
import { UserRequestDto } from 'src/app/commons/dto/request/user-request.dto';
import { UserResponseDto } from 'src/app/commons/dto/response/user-response.dto';
import { BASE_URLS } from 'src/app/config/service.url';

@Injectable()
export class AuthService {
  constructor(private readonly httpService: HttpService) {}

  async create(user: UserRequestDto): Promise<UserResponseDto> {
    try {
      const response = await firstValueFrom(
        this.httpService.post(`${BASE_URLS.USERS_SERVICE}/auth/register`, user),
      );
      return response.data;
    } catch (error) {
      throw new HttpException(
        error.response ? error.response.data : 'Erro ao cadastrar usuário',
        error.response ? error.response.status : HttpStatus.BAD_REQUEST,
      );
    }
  }

  findAll() {
    return `This action returns all auth`;
  }

  findOne(id: number) {
    return `This action returns a #${id} auth`;
  }

  update(id: number, updateAuthDto: UpdateUserDto) {
    return `This action updates a #${id} auth`;
  }

  remove(id: number) {
    return `This action removes a #${id} auth`;
  }
}
