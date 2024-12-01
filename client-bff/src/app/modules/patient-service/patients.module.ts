import { Module } from '@nestjs/common';
import { PatientsService } from './patients.service';
import { PatientsController } from './patients.controller';
import { HttpRequestModule } from 'src/app/commons/http/http-request.module';
import { AppConfigService } from 'src/app/config/config.service';

@Module({
  imports: [HttpRequestModule],
  controllers: [PatientsController],
  providers: [PatientsService, AppConfigService],
})
export class PatientsModule {}
