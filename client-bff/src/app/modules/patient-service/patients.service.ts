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
      `${BASE_URLS.PATIENTS_SERVICE}/patients/${id}`,
    )
  }
  create(createPatientDto: PatientResponseDto) {
    return 'This action adds a new patient';
  }

  findAll() {
    return `This action returns all patients`;
  }

  findOne(id: number) {
    return `This action returns a #${id} patient`;
  }

  update(id: number, updatePatientDto: any) {
    return `This action updates a #${id} patient`;
  }

  remove(id: number) {
    return `This action removes a #${id} patient`;
  }
}
