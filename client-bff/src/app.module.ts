import { Module } from '@nestjs/common';
import { UsersModule } from './app/modules/users-serivce/users.module';
import { PatientsModule } from './app/modules/patients-service/patients.module';

@Module({
  imports: [UsersModule, PatientsModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
