export interface AuthRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  username: string;
  roles: string[];
}

export interface RegisterRequest {
  username: string;
  password: string;
  roles: string[];
}
