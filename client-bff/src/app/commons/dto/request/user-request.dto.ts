import { IsEmail, IsNotEmpty, IsString, MinLength, IsEnum } from 'class-validator'; 
import { UserRole } from '../../enums/user-role.enum';

export class UserRequestDto {
  
  @IsNotEmpty({ message: 'Nome não pode ser vazio!' })
  @IsString({ message: 'Nome deve ser uma string!' })
  name: string;

  @IsNotEmpty({ message: 'Email não pode ser vazio!' })
  @IsEmail({}, { message: 'Digite um email válido!' })
  email: string;

  @MinLength(6, { message: 'A senha precisa ter no mínimo 6 caracteres!' })
  password: string;

  @IsNotEmpty({ message: 'Contato não pode ser vazio!' })
  @IsString({ message: 'Contato deve ser uma string!' })
  contact: string;

  @IsEnum(UserRole, { message: 'Role inválido!' })
  role: UserRole;
}
