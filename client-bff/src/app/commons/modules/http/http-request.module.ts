import { Module } from '@nestjs/common';
import { HttpRequestService } from './http-request.service';
import { HttpModule } from '@nestjs/axios';
import { TokenStorageService } from '../token/token-storage.service';

@Module({
  imports: [HttpModule],
  exports: [HttpRequestService],
  providers: [HttpRequestService, TokenStorageService],
})
export class HttpRequestModule {}
