import { Controller, Get, Post, Body, Patch, Param, Delete, Query } from '@nestjs/common';
import { SchedulingServiceService } from './scheduling.service';
import { CreateSchedulingServiceDto } from './dto/create-scheduling.dto';
import { UpdateSchedulingServiceDto } from './dto/update-scheduling.dto';

@Controller('scheduling')
export class SchedulingServiceController {
  constructor(private readonly schedulingServiceService: SchedulingServiceService) {}

  @Get('/user/:id/date')
  findSchedules(@Param('id') id: string, @Query('date') date: any){
    return this.schedulingServiceService.findSchedules(id, date);
  }
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
