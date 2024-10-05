import { Injectable } from '@nestjs/common';
import { HttpRequestService } from 'src/app/commons/http/http-request.service';
import { BASE_URLS } from 'src/app/config/service.url';
import { SchedulingRequestDto } from './dto/scheduling-request.dto';

@Injectable()
export class SchedulingServiceService {
  
  constructor(private readonly httpRequestService: HttpRequestService) {}
  async findSchedules(id: string, date: string) {
    return await this.httpRequestService.request(
      'GET',
      `${BASE_URLS.PATIENTS_SERVICE}/scheduling/${id}/date?date=${date}`,
    );
  }
  async findAvailabeSchedules(id: string, date: any) {
    return await this.httpRequestService.request(
      'GET',
      `${BASE_URLS.PATIENTS_SERVICE}/scheduling/${id}/available?date=${date}`,
    );
  }
  async create(schedulingRequestDto: SchedulingRequestDto) {
    return await this.httpRequestService.request(
      'POST',
      `${BASE_URLS.PATIENTS_SERVICE}/scheduling`,
      schedulingRequestDto,
    );
  }

  findAll() {
    return `This action returns all schedulingService`;
  }

  findOne(id: number) {
    return `This action returns a #${id} schedulingService`;
  }

  async update(id: string, schedulingRequestDto: SchedulingRequestDto) {
    return await this.httpRequestService.request(
      'PUT',
      `${BASE_URLS.PATIENTS_SERVICE}/scheduling/${id}`,
      schedulingRequestDto,
    );
  }

  async remove(id: string) {
    return await this.httpRequestService.request(
      'DELETE',
      `${BASE_URLS.PATIENTS_SERVICE}/scheduling/${id}`,
    );
  }
}
