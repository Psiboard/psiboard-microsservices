import { Injectable } from '@nestjs/common';
import { CreateSchedulingServiceDto } from './dto/create-scheduling.dto';
import { UpdateSchedulingServiceDto } from './dto/update-scheduling.dto';
import { HttpRequestService } from 'src/app/commons/http/http-request.service';
import { BASE_URLS } from 'src/app/config/service.url';

@Injectable()
export class SchedulingServiceService {
  constructor(private readonly httpRequestService: HttpRequestService) {}
  async findSchedules(id: string, date: string) {
    return await this.httpRequestService.request(
      'GET',
      `${BASE_URLS.PATIENTS_SERVICE}/scheduling/${id}/date?date=${date}`,
    );
  }
  create(createSchedulingServiceDto: CreateSchedulingServiceDto) {
    return 'This action adds a new schedulingService';
  }

  findAll() {
    return `This action returns all schedulingService`;
  }

  findOne(id: number) {
    return `This action returns a #${id} schedulingService`;
  }

  update(id: number, updateSchedulingServiceDto: UpdateSchedulingServiceDto) {
    return `This action updates a #${id} schedulingService`;
  }

  remove(id: number) {
    return `This action removes a #${id} schedulingService`;
  }
}
