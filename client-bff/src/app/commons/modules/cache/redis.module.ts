import { Module } from '@nestjs/common';
import { RedisModule } from '@nestjs-modules/ioredis';

@Module({
  imports: [
    RedisModule.forRoot({
        type: 'single',
        options: {
            host: 'localhost',
            port: 6379,
        }
    }),
  ],
  exports: [RedisModule],
})
export class RedisCacheModule {}
