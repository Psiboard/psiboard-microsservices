import { Injectable } from '@nestjs/common';
import { PatientResponseDto } from './dto/patient-response.dto';
import { HttpRequestService } from 'src/app/commons/http/http-request.service';
import { BASE_URLS } from 'src/app/config/service.url';

@Injectable()
export class PatientsService {
  constructor(private readonly httpRequestService: HttpRequestService) {}

  async findUserPatients(id: string): Promise<PatientResponseDto[]> {
    return await this.httpRequestService.request(
      'GET',
      `${BASE_URLS.PATIENTS_SERVICE}/patients/user/${id}`,
    )
  }
  async create(createPatientDto: PatientResponseDto) {
    return await this.httpRequestService.request(
      'POST',
      `${BASE_URLS.PATIENTS_SERVICE}/patients`,
      createPatientDto,
    );
  }

  findAll() {
    return `This action returns all patients`;

  }

  async findOne(id: string) {
    return await this.httpRequestService.request(
      'GET',
      `${BASE_URLS.PATIENTS_SERVICE}/patients/patient/${id}`,
    );
  }

  update(id: number, updatePatientDto: any) {
    return `This action updates a #${id} patient`;
  }

  remove(id: number) {
    return `This action removes a #${id} patient`;
  }
}
