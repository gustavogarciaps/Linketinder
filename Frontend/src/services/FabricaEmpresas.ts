import { Empresa } from "../models/Empresa";
import { Vaga } from "../models/Vaga";
import { guardarCadastro, recuperarCadastro, deletarChaveCadastro } from "./ServicoArmazenamento"

export const instanciarEmpresa = (forms: HTMLFormElement) => {

          const empresa = new Empresa();
          const formData = new FormData(forms);

          empresa.nome = formData.get('nome_empresa') as string;
          empresa.email = formData.get('email_empresa') as string;
          empresa.inscricao = formData.get('inscricao_empresa') as string;
          empresa.cep = formData.get('CEP_empresa') as string;
          empresa.estado = formData.get('estado_empresa') as string;
          empresa.pais = formData.get('pais_empresa') as string;
          empresa.descricao = formData.get('descricao_empresa') as string;

          guardarCadastro('empresas', empresa);
}

export const atribuirVaga = (empresa: Empresa, vaga: Vaga) => {

          if (vaga && empresa) {

                    empresa.vagas.adicionarVaga(vaga);
                    deletarChaveCadastro('vagas');
                    guardarCadastro('vagas', vaga);
          }

          deletarChaveCadastro('empresas');
          guardarCadastro('empresas', empresa);
}



