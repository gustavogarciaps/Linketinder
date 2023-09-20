import { Empresa } from "../models/Empresa";
import { guardarCadastro, deletarChaveCadastro } from "./ServicoArmazenamento"

export const instanciarEmpresa = (forms: HTMLFormElement) => {
          
          const formData = new FormData(forms);
          const dados = {
                    nome: formData.get('nome_empresa') as string,
                    email: formData.get('email_empresa') as string,
                    inscricao: formData.get('inscricao_empresa') as string,
                    cep: formData.get('CEP_empresa') as string,
                    estado: formData.get('estado_empresa') as string,
                    pais: formData.get('pais_empresa') as string,
                    descricao: formData.get('descricao_empresa') as string,
          };

          const empresa = new Empresa(dados);

          console.log(empresa)

          guardarCadastro('empresas', empresa);
}




