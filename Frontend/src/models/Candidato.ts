import { Competencia } from "./Competencia";
import { Pessoa } from "./Pessoa";

export class Candidato extends Pessoa {
     private _linkedin: string;
     private _dataNascimento: Date | null;
     private _formacao: string;
     private _competencias: Competencia[] = [];

     constructor(dados: Candidato | any = {}) {
          const {
               nome = '',
               email = '',
               inscricao = '',
               cep = '',
               estado = '',
               pais = '',
               descricao = '',
               dataNascimento = null,
               formacao = '',
               competencias = []
          } = dados;

          super(nome, email, inscricao, cep, estado, pais, descricao);
          this._linkedin = dados.linkedin;
          this._dataNascimento = dataNascimento;
          this._formacao = formacao;
          this._competencias = competencias;
     }

     set linkedin(linkedin: string) {
          this._linkedin = linkedin;
     }

     get linkedin(): string {
          return this._linkedin;
     }

     get dataNascimento(): Date | null {
          return this._dataNascimento;
     }

     set dataNascimento(dataNascimento: Date | null) {
          this._dataNascimento = dataNascimento;
     }

     get formacao(): string {
          return this._formacao;
     }

     set formacao(formacao: string) {
          this._formacao = formacao;
     }

     get competencias(): Competencia[] {
          return this._competencias;
     }

     set competencias(competencias: Competencia[]) {
          this._competencias = competencias;
     }

     static fromJSON(json: any): Candidato {
          return new Candidato(json);
     }
}
