import { Module } from '@nestjs/common';
import { SchedulingServiceService } from './scheduling.service';
import { SchedulingServiceController } from './scheduling.controller';
import { HttpRequestModule } from 'src/app/commons/modules/http/http-request.module';
import { AppConfigService } from 'src/app/config/config.service';

@Module({
  imports: [HttpRequestModule],
  controllers: [SchedulingServiceController],
  providers: [SchedulingServiceService, AppConfigService],
})
export class SchedulingServiceModule {}
