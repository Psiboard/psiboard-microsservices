import { Module } from '@nestjs/common';
import { UsersModule } from './app/modules/users-serivce/users.module';
import { PatientsModule } from './app/modules/patients-service/patients.module';
import { HttpModule } from '@nestjs/axios';

@Module({
  imports: [UsersModule, PatientsModule, HttpModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
