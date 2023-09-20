import { RepositorioVagas } from '../repositories/RepositorioVagas';
import { Pessoa } from './Pessoa';

export class Empresa extends Pessoa {
          
          private _vagas: RepositorioVagas = new RepositorioVagas();

          constructor(dados: Empresa | any = {}) {
                    const {
                              nome = '',
                              email = '',
                              inscricao = '',
                              cep = '',
                              estado = '',
                              pais = '',
                              descricao = ''
                    } = dados;

                    super(nome, email, inscricao, cep, estado, pais, descricao);
          }

          get vagas() {
                    return this._vagas;
          }

          static fromJSON(json: any): Empresa {
                    return new Empresa(json);
          }
}
