export enum Role {
  ADMIN = 'ADMIN',
  RH = 'RH',
  GESTOR = 'GESTOR',
  FUNCIONARIO = 'FUNCIONARIO'
}

export interface Usuario {
  id?: number;
  username: string;
  email: string;
  role: Role;
  ativo?: boolean;
}

export interface UsuarioCreate extends Usuario {
  password: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  type: string;
  id: number;
  username: string;
  email: string;
  role: string;
}
