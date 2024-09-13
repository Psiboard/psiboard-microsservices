import { Injectable } from '@nestjs/common';
import { UpdateUserDto } from './dto/request/update-user.dto';
import { UserRequestDto } from './dto/request/user-request.dto';

@Injectable()
export class UsersService {
  create(createUserDto: UserRequestDto) {
    return 'This action adds a new user';
  }

  findAll() {
    return `This action returns all users`;
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
