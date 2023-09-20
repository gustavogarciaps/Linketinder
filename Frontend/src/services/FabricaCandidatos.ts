import { Candidato } from "../models/Candidato";
import { Competencia } from "../models/Competencia";
import { guardarCadastro, recuperarCadastro, deletarChaveCadastro } from "./ServicoArmazenamento"
import { instanciarCompetencias } from "./FabricaCompetencias";

export const instanciarCandidato = (forms: HTMLFormElement) => {

     const candidato = new Candidato();
     const formData = new FormData(forms);

     candidato.nome = formData.get('nome_candidato') as string;
     candidato.email = formData.get('email_candidato') as string;
     candidato.inscricao = formData.get('inscricao_candidato') as string;
     candidato.cep = formData.get('CEP_candidato') as string;
     candidato.estado = formData.get('estado_candidato') as string;
     candidato.pais = formData.get('pais_candidato') as string;
     candidato.descricao = formData.get('descricao_candidato') as string;
     const dataNascimento = formData.get('data_nascimento_candidato') as string;
     candidato.dataNascimento = new Date(dataNascimento);

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

          candidato.competencias.adicionarCompetencias(selecionados);

          deletarChaveCadastro('candidatos');
          guardarCadastro('candidatos', candidato);
     }

};


