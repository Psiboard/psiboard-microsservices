import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  Query,
} from '@nestjs/common';
import { PatientsService } from './patients.service';
import { PatientResponseDto } from './dto/patient-response.dto';
import { PatientRequestDto } from './dto/patient-request.dto';

@Controller('patients')
export class PatientsController {
  constructor(private readonly patientsService: PatientsService) {}

  @Post()
  create(@Body() createPatientDto: PatientRequestDto) {
    return this.patientsService.create(createPatientDto);
  }

  @Get('/user/:id/patients')
  findSchedules(@Param('id') id: string): Promise<PatientResponseDto[]> {
    return this.patientsService.findUserPatients(id);
  }

  @Get('/patient/:id')
  findOne(@Param('id') id: string) {
    return this.patientsService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updatePatientDto: PatientRequestDto) {
    return this.patientsService.update(+id, updatePatientDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.patientsService.remove(+id);
  }
}
