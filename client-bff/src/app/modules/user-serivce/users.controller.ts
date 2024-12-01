import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  Query,
  NotFoundException,
} from '@nestjs/common';
import { UsersService } from './users.service';
import { UpdateUserDto } from '../../commons/dto/request/update-user.dto';
import { UserResponseDto } from '../../commons/dto/response/user-response.dto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  findAll() {
    return this.usersService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string): UserResponseDto {
    // return this.usersService.findOne(+id);
    return null;
  }
  @Get('/user/:email')
  findByEmail(@Param('email') email: string): any {
    const user = this.usersService.findByEmail(email);
    if(!user) {
      throw new NotFoundException('User not found');
    }
    return user;
  }
  
  @Patch(':id')
  update(
    @Param('id') id: string,
    @Body() updateUserDto: UpdateUserDto,
  ): UserResponseDto {
    // return this.usersService.update(+id, updateUserDto);
    return null;
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.usersService.remove(+id);
  }
}
