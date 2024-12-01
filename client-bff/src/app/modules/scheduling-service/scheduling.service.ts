import { Injectable } from '@nestjs/common';
import { HttpRequestService } from 'src/app/commons/http/http-request.service';
import { SchedulingRequestDto } from './dto/scheduling-request.dto';
import { AppConfigService } from 'src/app/config/config.service';

@Injectable()
export class SchedulingServiceService {
  constructor(
    private readonly httpRequestService: HttpRequestService,
    private readonly appConfigService: AppConfigService,
  ) {}
  async findSchedules(id: string, date: string) {
    return await this.httpRequestService.request(
      'GET',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/scheduling/${id}/date?date=${date}`,
    );
  }
  async findAvailabeSchedules(id: string, date: any) {
    return await this.httpRequestService.request(
      'GET',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/scheduling/${id}/available?date=${date}`,
    );
  }
  async create(schedulingRequestDto: SchedulingRequestDto) {
    return await this.httpRequestService.request(
      'POST',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/scheduling`,
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
      `${this.appConfigService.baseUrls.USERS_SERVICE}/scheduling/${id}`,
      schedulingRequestDto,
    );
  }

  async remove(id: string) {
    return await this.httpRequestService.request(
      'DELETE',
      `${this.appConfigService.baseUrls.USERS_SERVICE}/scheduling/${id}`,
    );
  }
}
