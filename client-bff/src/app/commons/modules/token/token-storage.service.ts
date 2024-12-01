import { Injectable } from '@nestjs/common';
import { InjectRedis } from '@nestjs-modules/ioredis';
import { Redis } from 'ioredis';

@Injectable()
export class TokenStorageService {
  private readonly tokenKey = 'access_token';

  constructor(@InjectRedis() private readonly redis: Redis) {}

  async setAccessToken(token: string): Promise<void> {
    await this.redis.set(this.tokenKey, token, 'EX', 3600);
  }

  async getAccessToken(): Promise<string | null> {
    return await this.redis.get(this.tokenKey);
  }

  async clearAccessToken(): Promise<void> {
    await this.redis.del(this.tokenKey);
  }
}
