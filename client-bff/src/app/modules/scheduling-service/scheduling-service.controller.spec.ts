import { Test, TestingModule } from '@nestjs/testing';
import { SchedulingServiceController } from './scheduling-service.controller';
import { SchedulingServiceService } from './scheduling-service.service';

describe('SchedulingServiceController', () => {
  let controller: SchedulingServiceController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [SchedulingServiceController],
      providers: [SchedulingServiceService],
    }).compile();

    controller = module.get<SchedulingServiceController>(SchedulingServiceController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
