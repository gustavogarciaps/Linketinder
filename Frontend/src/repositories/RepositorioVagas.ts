import { Vaga } from "../models/Vaga";

export class RepositorioVagas {

     private vagas: Vaga[] = []

     public adicionarVaga(vaga: Vaga): void {
          this.vagas.push(vaga)
     }

     public removeVaga(vaga: Vaga): void {
          const index = this.vagas.indexOf(vaga)
          this.vagas.splice(index, 1)
     }

     public listarVagas(): Vaga[] {
          if(this.vagas.length == 0){
               return []
          }
          return this.vagas
     }
}