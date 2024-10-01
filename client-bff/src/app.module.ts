import { Module } from '@nestjs/common';
import { UsersModule } from './app/modules/users-serivce/users.module';
import { PatientsModule } from './app/modules/patients-service/patients.module';
import { HttpModule } from '@nestjs/axios';
import { AuthModule } from './app/modules/auth/auth.module';

@Module({
  imports: [UsersModule, PatientsModule, HttpModule, AuthModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
