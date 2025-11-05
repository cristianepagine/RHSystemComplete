export enum StatusFerias {
  PENDENTE = 'PENDENTE',
  APROVADA = 'APROVADA',
  REJEITADA = 'REJEITADA'
}

export interface Ferias {
  id?: number;
  funcionarioId: number;
  funcionarioNome?: string;
  dataInicio: string;
  dataFim: string;
  status: StatusFerias;
  diasSolicitados: number;
  observacao?: string;
}
