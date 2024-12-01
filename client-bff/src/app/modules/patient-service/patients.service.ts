import { Injectable } from '@nestjs/common';
import { PatientResponseDto } from './dto/patient-response.dto';
import { HttpRequestService } from 'src/app/commons/modules/http/http-request.service';
import { PatientRequestDto } from './dto/patient-request.dto';
import { AppConfigService } from 'src/app/config/config.service';

@Injectable()
export class PatientsService {
  constructor(
    private readonly httpRequestService: HttpRequestService,
    private readonly appConfigService: AppConfigService,
  ) {}

  async findUserPatients(id: string): Promise<PatientResponseDto[]> {
    return await this.httpRequestService.request(
      'GET',
      `${this.appConfigService.baseUrls.PATIENTS_SERVICE}/patients/user/${id}`,
    );
  }
  async create(createPatientDto: PatientRequestDto) {
    return await this.httpRequestService.request(
      'POST',
      `${this.appConfigService.baseUrls.PATIENTS_SERVICE}/patients`,
      createPatientDto,
    );
  }

  async findOne(id: string) {
    return await this.httpRequestService.request(
      'GET',
      `${this.appConfigService.baseUrls.PATIENTS_SERVICE}/patients/patient/${id}`,
    );
  }

  async update(id: number, updatePatientDto: PatientRequestDto) {
    return await this.httpRequestService.request(
      'PUT',
      `${this.appConfigService.baseUrls.PATIENTS_SERVICE}/patients/${id}`,
      updatePatientDto,
    );
  }

  async remove(id: number) {
    return await this.httpRequestService.request(
      'DELETE',
      `${this.appConfigService.baseUrls.PATIENTS_SERVICE}/patients/${id}`,
    );
  }
}
