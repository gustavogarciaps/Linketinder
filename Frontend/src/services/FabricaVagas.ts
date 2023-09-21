import { Vaga } from "../models/Vaga";
import { Competencia } from "../models/Competencia";
import { guardarCadastro, recuperarCadastro, deletarChaveCadastro, atualizarCadastro } from "./ServicoArmazenamento"
import { instanciarCompetencias } from "./FabricaCompetencias";
import { Empresa } from "../models/Empresa";

export const instanciarVaga = (forms: HTMLFormElement) => {

          const formData = new FormData(forms);
          const dados = {
                    nome: formData.get('nome_vaga') as string,
                    descricao: formData.get('descricao_vaga') as string,
                    criacao: new Date(formData.get('data_publicacao_vaga') as string),
                    competencias: [] as Competencia[]
          };

          const vaga = new Vaga(dados);

          console.log(vaga)

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

          if (vaga) {

                    vaga.competencias = [...selecionados];

                    const empresas = recuperarCadastro('empresas') as Empresa[];
                    const empresa = new Empresa(empresas[0])

                    empresa.vagas.push(vaga)

                    console.log(empresa)

                    atualizarCadastro('empresas', empresa, 0)
          }
};

