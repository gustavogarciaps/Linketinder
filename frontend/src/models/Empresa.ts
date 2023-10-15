import { Pessoa } from './Pessoa';
import { Vaga } from './Vaga';

export class Empresa extends Pessoa {

          private _vagas: Vaga[] = [];

          constructor(dados: Empresa | any = {}) {
                    const {
                              nome = '',
                              email = '',
                              inscricao = '',
                              cep = '',
                              estado = '',
                              pais = '',
                              descricao = '',
                              vagas = []
                    } = dados;

                    super(nome, email, inscricao, cep, estado, pais, descricao);
                    this._vagas = vagas;
          }

          get vagas(): Vaga[] {
                    return this._vagas;
          }

          set vagas(vagas: Vaga[]) {
                    this._vagas = vagas;
          }

}
