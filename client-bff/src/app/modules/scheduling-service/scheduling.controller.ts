import { Controller, Get, Post, Body, Patch, Param, Delete, Query, Put } from '@nestjs/common';
import { SchedulingServiceService } from './scheduling.service';
import { SchedulingRequestDto } from './dto/scheduling-request.dto';
import { SchedulingResponseDto } from './dto/scheduling-response.dto';

@Controller('scheduling')
export class SchedulingServiceController {
  constructor(private readonly schedulingServiceService: SchedulingServiceService) {}

  @Get('/user/:id/date')
  findSchedules(@Param('id') id: string, @Query('date') date: any){
    return this.schedulingServiceService.findSchedules(id, date);
  }

  @Get('/user/:id/available')
  findAvailabeSchedules(@Param('id') id: string, @Query('date') date: any){
    return this.schedulingServiceService.findAvailabeSchedules(id, date);
  }
  @Post()
  create(@Body() schedulingRequestDto: SchedulingRequestDto) {
    return this.schedulingServiceService.create(schedulingRequestDto);
  }

  @Get()
  findAll() {
    return this.schedulingServiceService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.schedulingServiceService.findOne(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() schedulingRequestDto: SchedulingRequestDto) {
    return this.schedulingServiceService.update(id, schedulingRequestDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.schedulingServiceService.remove(id);
  }
}
