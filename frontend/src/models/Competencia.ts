export class Competencia {

     private _nome: string;

     constructor(dados: Competencia | any = {}) {
          const {
               nome = ''
          } = dados;

          this._nome = nome;
     }

     get nome() {
          return this._nome;
     }
}