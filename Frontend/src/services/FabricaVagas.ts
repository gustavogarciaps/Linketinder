import { Vaga } from "../models/Vaga";
import { Competencia } from "../models/Competencia";
import { guardarCadastro, recuperarCadastro, deletarChaveCadastro } from "./ServicoArmazenamento"
import { instanciarCompetencias } from "./FabricaCompetencias";
import { atribuirVaga } from "./FabricaEmpresas";
import { Empresa } from "../models/Empresa";

export const instanciarVaga = (forms: HTMLFormElement) => {

          const vaga = new Vaga();
          const formData = new FormData(forms);

          vaga.nome = formData.get('nome_vaga') as string;
          vaga.descricao = formData.get('descricao_vaga') as string;
          const dataCriacao = formData.get('data_publicacao_vaga') as string;
          vaga.criacao = new Date(dataCriacao);

          selecionarCompetencias(forms, vaga);

}

const selecionarCompetencias = (forms: HTMLFormElement, vaga: Vaga) => {

          let selecionados: Competencia[] = [];

          const competenciasCadastradas = instanciarCompetencias();
          const competenciasSelecionadas = forms.querySelectorAll('.btn-check');

          competenciasSelecionadas.forEach(checkbox => {

                    if (checkbox instanceof HTMLInputElement && checkbox.checked) {

                              const checkboxId = parseInt(checkbox.id.split('-')[2]) as number;

                              selecionados.push(competenciasCadastradas[checkboxId]);
                    }

          });

          vaga.competencias?.adicionarCompetencias(selecionados);

          atribuirVaga(recuperarCadastro('empresas')[0] as Empresa, vaga);

};

