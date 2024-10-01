import { Controller, Post, Body } from '@nestjs/common';
import { AuthService } from './auth.service';
import { UserRequestDto } from 'src/app/commons/dto/request/user-request.dto';
import { AuthLoginDto } from './dto/auth-login.dto';

@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @Post('register')
  create(@Body() createUserDto: UserRequestDto) {
    return this.authService.create(createUserDto);
  }

  @Post('login')
  login(@Body() login: AuthLoginDto) {
    return this.authService.login(login);
  }
}
