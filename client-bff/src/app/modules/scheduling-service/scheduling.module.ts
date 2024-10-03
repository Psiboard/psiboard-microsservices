import { Module } from '@nestjs/common';
import { SchedulingServiceService } from './scheduling.service';
import { SchedulingServiceController } from './scheduling.controller';
import { HttpRequestModule } from 'src/app/commons/http/http-request.module';

@Module({
  imports: [HttpRequestModule],
  controllers: [SchedulingServiceController],
  providers: [SchedulingServiceService],
})
export class SchedulingServiceModule {}
