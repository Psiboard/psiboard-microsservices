import { PartialType } from '@nestjs/mapped-types';
import { CreateSchedulingServiceDto } from './create-scheduling.dto';

export class UpdateSchedulingServiceDto extends PartialType(CreateSchedulingServiceDto) {}
