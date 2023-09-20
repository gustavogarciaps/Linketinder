import { RepositorioVagas } from '../repositories/RepositorioVagas';
import { Pessoa } from './Pessoa';

export class Empresa extends Pessoa {

          constructor(
                    _nome?: string,
                    _email?: string,
                    _inscricao?: string,
                    _cep?: string,
                    _estado?: string,
                    _pais?: string,
                    _descricao?: string,
                    private _vagas = new RepositorioVagas()
          ) {
                    super(_nome, _email, _inscricao, _cep, _estado, _pais, _descricao);
          }

          get vagas() {
                    return this._vagas;
          }
}