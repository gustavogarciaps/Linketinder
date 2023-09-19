export abstract class Pessoa {

     constructor(
          private _nome?: string,
          private _email?: string,
          private _inscricao?: string,
          private _cep?: string,
          private _estado?: string,
          private _pais?: string,
          private _descricao?: string
     ) { }

     get nome() {
          return this._nome ?? "";
     }

     set nome(nome: string) {
          this._nome = nome;
     }

     get email() {
          return this._email ?? "";
     }

     set email(email: string) {
          this._email = email;
     }

     get inscricao() {
          return this._inscricao ?? "";
     }

     set inscricao(inscricao: string) {
          this._inscricao = inscricao;
     }

     get cep() {
          return this._cep ?? "";
     }

     set cep(cep: string) {
          this._cep = cep;
     }

     get estado() {
          return this._estado ?? "";
     }

     set estado(estado: string) {
          this._estado = estado;
     }

     get pais(): string {
          return this._pais ?? "";
     }

     set pais(pais: string) {
          this._pais = pais;
     }

     get descricao() {
          return this._descricao ?? "";
     }

     set descricao(descricao: string) {
          this._descricao = descricao;
     }
}
