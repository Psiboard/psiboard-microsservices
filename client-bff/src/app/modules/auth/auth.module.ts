import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { AuthController } from './auth.controller';
import { HttpRequestModule } from 'src/app/commons/modules/http/http-request.module';
import { AppConfigService } from 'src/app/config/config.service';
import { TokenStorageService } from 'src/app/commons/modules/token/token-storage.service';
import { RedisCacheModule } from 'src/app/commons/modules/cache/redis.module';

@Module({
  imports: [HttpRequestModule, RedisCacheModule],
  controllers: [AuthController],
  providers: [AuthService, AppConfigService, TokenStorageService],
})
export class AuthModule {}
