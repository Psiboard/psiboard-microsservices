import { Module } from '@nestjs/common';
import { UsersModule } from './app/modules/user-serivce/users.module';
import { PatientsModule } from './app/modules/patient-service/patients.module';
import { HttpModule } from '@nestjs/axios';
import { AuthModule } from './app/modules/auth/auth.module';
import { SchedulingServiceModule } from './app/modules/scheduling-service/scheduling-service.module';

@Module({
  imports: [UsersModule, PatientsModule, HttpModule, AuthModule, SchedulingServiceModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
