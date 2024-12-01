import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { firstValueFrom } from 'rxjs';
import { TokenStorageService } from '../token/token-storage.service';

@Injectable()
export class HttpRequestService {
  constructor(
    private readonly httpService: HttpService,
    private readonly tokenStorageService: TokenStorageService
  ) {}

  async request(
    method: 'GET' | 'POST' | 'PUT' | 'DELETE',
    url: string,
    data?: any,
  ): Promise<any> {

    try {
      const token = await this.tokenStorageService.getAccessToken();
      const options = {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      };

      const methodsMap = {
        GET: () => firstValueFrom(this.httpService.get(url, options)),
        POST: () => firstValueFrom(this.httpService.post(url, data, options)),
        PUT: () => firstValueFrom(this.httpService.put(url, data, options)),
        DELETE: () => firstValueFrom(this.httpService.delete(url, options)),
      };

      const response = await methodsMap[method]();
      return response.data;
    } catch (error) {
      console.log(error);
      throw new HttpException(
        error.response ? error.response.data : 'Erro ao fazer a requisição',
        error.response ? error.response.status : HttpStatus.BAD_REQUEST,
      );
    }
  }
}
