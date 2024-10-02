import { Module } from '@nestjs/common';
import { SchedulingServiceService } from './scheduling-service.service';
import { SchedulingServiceController } from './scheduling-service.controller';

@Module({
  controllers: [SchedulingServiceController],
  providers: [SchedulingServiceService],
})
export class SchedulingServiceModule {}
