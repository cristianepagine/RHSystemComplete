export interface Funcionario {
  id?: number;
  nome: string;
  cpf: string;
  rg: string;
  dataNascimento: string;
  dataAdmissao: string;
  salario: number;
  status?: boolean;
  fotoUrl?: string;
  usuarioId?: number;
  cargoId?: number;
  cargoNome?: string;
  departamentoId?: number;
  departamentoNome?: string;
}
