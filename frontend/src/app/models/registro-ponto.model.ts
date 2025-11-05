export enum TipoPonto {
  ENTRADA = 'ENTRADA',
  SAIDA = 'SAIDA'
}

export interface RegistroPonto {
  id?: number;
  funcionarioId: number;
  funcionarioNome?: string;
  dataHora: string;
  tipo: TipoPonto;
}
