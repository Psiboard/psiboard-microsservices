import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
} from '@nestjs/common';
import { UsersService } from './users.service';
import { UpdateUserDto } from './dto/request/update-user.dto';
import { UserRequestDto } from './dto/request/user-request.dto';
import { UserResponseDto } from './dto/response/user-response.dto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Post()
  create(@Body() createUserDto: UserRequestDto): UserResponseDto {
    // return this.usersService.create(createUserDto);
    return null;
  }

  @Get()
  findAll(): UserResponseDto[] {
    // return this.usersService.findAll();
    return null;
  }

  @Get(':id')
  findOne(@Param('id') id: string): UserResponseDto {
    // return this.usersService.findOne(+id);
    return null;
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateUserDto: UpdateUserDto): UserResponseDto {
    // return this.usersService.update(+id, updateUserDto);
    return null;
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.usersService.remove(+id);
  }
}
