import { Injectable } from '@nestjs/common';
import { PatientResponseDto } from './dto/patient-response.dto';
import { HttpRequestService } from 'src/app/commons/http/http-request.service';
import { BASE_URLS } from 'src/app/config/service.url';
import { PatientRequestDto } from './dto/patient-request.dto';

@Injectable()
export class PatientsService {
  constructor(private readonly httpRequestService: HttpRequestService) {}

  async findUserPatients(id: string): Promise<PatientResponseDto[]> {
    return await this.httpRequestService.request(
      'GET',
      `${BASE_URLS.PATIENTS_SERVICE}/patients/user/${id}`,
    )
  }
  async create(createPatientDto: PatientRequestDto) {
    return await this.httpRequestService.request(
      'POST',
      `${BASE_URLS.PATIENTS_SERVICE}/patients`,
      createPatientDto,
    );
  }

  async findOne(id: string) {
    return await this.httpRequestService.request(
      'GET',
      `${BASE_URLS.PATIENTS_SERVICE}/patients/patient/${id}`,
    );
  }

  async update(id: number, updatePatientDto: PatientRequestDto) {
    return await this.httpRequestService.request(
      'PUT',
      `${BASE_URLS.PATIENTS_SERVICE}/patients/${id}`,
      updatePatientDto,
    );
  }

  async remove(id: number) {
    return await this.httpRequestService.request(
      'DELETE',
      `${BASE_URLS.PATIENTS_SERVICE}/patients/${id}`,
    ); 
  }
}
