import { Injectable } from '@nestjs/common';
import { CreateSchedulingServiceDto } from './dto/create-scheduling-service.dto';
import { UpdateSchedulingServiceDto } from './dto/update-scheduling-service.dto';

@Injectable()
export class SchedulingServiceService {
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
