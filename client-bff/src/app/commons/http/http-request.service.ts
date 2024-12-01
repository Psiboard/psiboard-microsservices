import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { firstValueFrom } from 'rxjs';

@Injectable()
export class HttpRequestService {
  constructor(
    private readonly httpService: HttpService,
  ) {}

  async request(
    method: 'GET' | 'POST' | 'PUT' | 'DELETE',
    url: string,
    data?: any,
    token?: string,
  ): Promise<any> {

    try {
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
