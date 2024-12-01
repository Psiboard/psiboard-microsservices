import { Module } from '@nestjs/common';
import { UsersService } from './users.service';
import { UsersController } from './users.controller';
import { HttpRequestModule } from 'src/app/commons/modules/http/http-request.module';
import { AppConfigService } from 'src/app/config/config.service';


@Module({
  imports: [HttpRequestModule],
  controllers: [UsersController],
  providers: [UsersService, AppConfigService],
})
export class UsersModule {}
