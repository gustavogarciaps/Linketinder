import { Vaga } from "../models/Vaga";
import { Competencia } from "../models/Competencia";
import { guardarCadastro, recuperarCadastro, deletarChaveCadastro, atualizarCadastro } from "./ServicoArmazenamento";
import { instanciarCompetencias } from "./FabricaCompetencias";
import { Empresa } from "../models/Empresa";

export const instanciarVaga = (forms: HTMLFormElement) => {

          const formData = new FormData(forms);
          const dados = {
                    nome: formData.get('nome_vaga') as string,
                    descricao: formData.get('descricao_vaga') as string,
                    criacao: new Date(formData.get('data_publicacao_vaga') as string)
          };

          const vaga = new Vaga(dados);

          selecionarCompetencias(forms, vaga);
}

const selecionarCompetencias = (forms: HTMLFormElement, vaga: Vaga) => {

          const selecionados: Competencia[] = [];

          const competenciasCadastradas = instanciarCompetencias();
          const competenciasSelecionadas = forms.querySelectorAll('.btn-check');

          competenciasSelecionadas.forEach(checkbox => {
                    if (checkbox instanceof HTMLInputElement && checkbox.checked) {
                              const checkboxId = parseInt(checkbox.id.split('-')[2]) as number;
                              selecionados.push(competenciasCadastradas[checkboxId]);
                    }
          });

          vaga.competencias = [...selecionados];
          atribuirVagaEmpresa(vaga);
}

const atribuirVagaEmpresa = (vaga: Vaga) => {

          if (vaga) {

                    const empresasJSON = recuperarCadastro('empresas');

                    if (empresasJSON.length > 0) {
                              const empresa: Empresa = desestruturarJSON(empresasJSON[0]);
                              empresa.vagas.push(vaga);

                              console.log("fim lógica selecinarCompetencias")

                              const empresaJSON = estruturarJSON(empresa);
                              atualizarCadastro('empresas', empresaJSON, 0);
                    }
          };
}

const desestruturarJSON = (empresaJSON: any) => {

          const vagasJSON = empresaJSON.vagas || [];

          const vagas: Vaga[] = vagasJSON.map((vagaJSON: any) => {
                    const competenciasJSON = vagaJSON.competencias || [];

                    const competencias: Competencia[] = competenciasJSON.map(
                              (competenciaJSON: any) => new Competencia(competenciaJSON.nome)
                    );

                    return new Vaga({
                              nome: vagaJSON.nome,
                              descricao: vagaJSON.descricao,
                              criacao: new Date(vagaJSON.criacao),
                              competencias: competencias
                    });
          });

          const empresa = new Empresa({
                    nome: empresaJSON.nome,
                    email: empresaJSON.email,
                    inscricao: empresaJSON.inscricao,
                    cep: empresaJSON.cep,
                    estado: empresaJSON.estado,
                    pais: empresaJSON.pais,
                    descricao: empresaJSON.descricao,
                    vagas: vagas
          });

          return empresa;
};


const estruturarJSON = (empresa: Empresa) => {
          const vagasJSON: any[] = empresa.vagas.map(vaga => {
                    return {
                              nome: vaga.nome,
                              descricao: vaga.descricao,
                              criacao: vaga.criacao,
                              competencias: vaga.competencias.map(competencia => competencia.nome)
                    };
          });

          const empresaJSON = {
                    nome: empresa.nome,
                    email: empresa.email,
                    inscricao: empresa.inscricao,
                    cep: empresa.cep,
                    estado: empresa.estado,
                    pais: empresa.pais,
                    descricao: empresa.descricao,
                    vagas: vagasJSON
          };

          console.error(empresaJSON)
          return empresaJSON;
}




/*
[{
          nome = '',
          email = '',
          inscricao = '',
          cep = '',
          estado = '',
          pais = '',
          descricao = '',
          vagas = [{
                    nome = '',
                    descricao = '',
                    criacao = '',
                    competencias = [{
                              nome = ''
          }]
}]
*/



