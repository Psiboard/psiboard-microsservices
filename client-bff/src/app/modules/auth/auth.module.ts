import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { AuthController } from './auth.controller';
import { HttpRequestModule } from 'src/app/commons/http/http-request.module';
import { AppConfigService } from 'src/app/config/config.service';

@Module({
  imports: [HttpRequestModule],
  controllers: [AuthController],
  providers: [AuthService, AppConfigService],
})
export class AuthModule {}
