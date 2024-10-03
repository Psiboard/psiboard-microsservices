import { Module } from '@nestjs/common';
import { PatientsService } from './patients.service';
import { PatientsController } from './patients.controller';
import { HttpRequestModule } from 'src/app/commons/http/http-request.module';

@Module({
  imports: [HttpRequestModule],
  controllers: [PatientsController],
  providers: [PatientsService],
})
export class PatientsModule {}
