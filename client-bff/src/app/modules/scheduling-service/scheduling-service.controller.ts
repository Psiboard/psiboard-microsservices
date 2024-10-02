import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { SchedulingServiceService } from './scheduling-service.service';
import { CreateSchedulingServiceDto } from './dto/create-scheduling-service.dto';
import { UpdateSchedulingServiceDto } from './dto/update-scheduling-service.dto';

@Controller('scheduling-service')
export class SchedulingServiceController {
  constructor(private readonly schedulingServiceService: SchedulingServiceService) {}

  @Post()
  create(@Body() createSchedulingServiceDto: CreateSchedulingServiceDto) {
    return this.schedulingServiceService.create(createSchedulingServiceDto);
  }

  @Get()
  findAll() {
    return this.schedulingServiceService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.schedulingServiceService.findOne(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateSchedulingServiceDto: UpdateSchedulingServiceDto) {
    return this.schedulingServiceService.update(+id, updateSchedulingServiceDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.schedulingServiceService.remove(+id);
  }
}
