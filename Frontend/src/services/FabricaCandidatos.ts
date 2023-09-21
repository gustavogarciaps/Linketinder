import { Candidato } from "../models/Candidato";
import { Competencia } from "../models/Competencia";
import { guardarCadastro, recuperarCadastro, deletarChaveCadastro } from "./ServicoArmazenamento"
import { instanciarCompetencias } from "./FabricaCompetencias";

export const instanciarCandidato = (forms: HTMLFormElement) => {

     const formData = new FormData(forms);
     const dados = {
          nome: formData.get('nome_candidato') as string,
          email: formData.get('email_candidato') as string,
          inscricao: formData.get('inscricao_candidato') as string,
          cep: formData.get('CEP_candidato') as string,
          estado: formData.get('estado_candidato') as string,
          pais: formData.get('pais_candidato') as string,
          descricao: formData.get('descricao_candidato') as string,
          dataNascimento: new Date(formData.get('descricao_candidato') as string) as Date
     };

     const candidato = new Candidato(dados);

     selecionarCompetencias(forms, candidato);
}

const selecionarCompetencias = (forms: HTMLFormElement, candidato: Candidato) => {

     let selecionados: Competencia[] = [];

     const competenciasCadastradas = instanciarCompetencias();
     const competenciasSelecionadas = forms.querySelectorAll('.btn-check');

     competenciasSelecionadas.forEach(checkbox => {

          if (checkbox instanceof HTMLInputElement && checkbox.checked) {

               const checkboxId = parseInt(checkbox.id.split('-')[2]) as number;

               selecionados.push(competenciasCadastradas[checkboxId]);
          }

     });

     if (candidato) {

          candidato.competencias = [...selecionados];
          guardarCadastro('candidatos', candidato);
     }

};


