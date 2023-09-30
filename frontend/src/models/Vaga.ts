import { Competencia } from "./Competencia";
import { Empresa } from "./Empresa";

export class Vaga {

     private _nome?: string;
     private _descricao?: string;
     private _criacao?: Date | null;
     private _competencias: Competencia[] = [];

     constructor(dados: Empresa | any = {}) {
          const {
               nome = '',
               descricao = '',
               criacao = null,
               competencias = []
          } = dados;

          this._nome = nome;
          this._descricao = descricao;
          this._criacao = criacao;
          this._competencias = competencias;

     }

     get nome(): string {
          return this._nome ?? "";
     }

     set nome(nome: string) {
          this._nome = nome;
     }

     get descricao(): string {
          return this._descricao ?? "";
     }

     set descricao(descricao: string) {
          this._descricao = descricao;
     }

     get criacao(): Date | null {
          return this._criacao ?? null;
     }

     set criacao(criacao: Date | null) {
          this._criacao = criacao ?? null;
     }

     get competencias(): Competencia[] {
          return this._competencias;
     }

     set competencias(competencias: Competencia[]) {
          this._competencias = competencias;
     }
}