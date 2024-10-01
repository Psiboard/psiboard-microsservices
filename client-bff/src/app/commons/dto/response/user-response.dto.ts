import { UserRole } from '../../enums/user-role.enum';

export class UserResponseDto {
  id: string;
  name: string;
  email: string;
  contact: string;
  role: UserRole;
  
}
