export abstract class Pessoa {

     constructor(private nome: string,
          private email: string,
          private inscricao: string,
          private cep: string,
          private estado: string,
          private pais: string,
          private descricao: string) {
     }

     get Nome() {
          return this.nome;
     }

     get Email() {
          return this.email;
     }

     get Inscricao() {
          return this.inscricao;
     }

     get Cep() {
          return this.cep;
     }

     get Estado() {
          return this.estado;
     }

     get Pais() {
          return this.pais;
     }

     get Descricao() {
          return this.descricao;
     }
}