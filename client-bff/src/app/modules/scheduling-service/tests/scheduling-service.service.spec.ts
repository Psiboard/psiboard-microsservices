import { Test, TestingModule } from '@nestjs/testing';
import { SchedulingServiceService } from '../scheduling.service';

describe('SchedulingServiceService', () => {
  let service: SchedulingServiceService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [SchedulingServiceService],
    }).compile();

    service = module.get<SchedulingServiceService>(SchedulingServiceService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
