import { IsEmail, IsNotEmpty, MinLength } from 'class-validator';

export class AuthLoginDto {
  @IsNotEmpty({ message: 'Email não pode ser vazio!' })
  @IsEmail({}, { message: 'Digite um email válido!' })
  email: string;

  @MinLength(6, { message: 'A senha precisa ter no mínimo 6 caracteres!' })
  password: string;
}
