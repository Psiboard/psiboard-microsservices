import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { AuthService } from './auth.service';
import { UserRequestDto } from 'src/app/commons/dto/request/user-request.dto';


@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @Post('register')
  create(@Body() createUserDto: UserRequestDto) {
    return this.authService.create(createUserDto);
  }
}
