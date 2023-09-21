import { Competencia } from "../models/Competencia";
import { Empresa } from "../models/Empresa";
import { Vaga } from "../models/Vaga";
import { instanciarCompetencias } from "./FabricaCompetencias";
import { deletarChaveCadastro, guardarCadastro } from "./ServicoArmazenamento";

export const mockVagas = () => {

          const competencias = instanciarCompetencias();

          const Mockempresas = [
                    {
                              nome: 'Nestlé',
                              email: 'contato@nestle.com',
                              inscricao: '12.156.489/0001-48',
                              cep: '45200000',
                              estado: 'São Paulo',
                              pais: 'Brasil',
                              descricao: 'Somos a maior empresa do setor de alimentos, levamos alegria para as famílias.',
                    },
                    {
                              nome: 'Google',
                              email: 'contato@google.com',
                              inscricao: '36.563.789/0001-12',
                              cep: '94000000',
                              estado: 'Califórnia',
                              pais: 'Estados Unidos',
                              descricao: 'Tornando a informação acessível e útil para todo o mundo.',
                    },
                    {
                              nome: 'Microsoft',
                              email: 'contato@microsoft.com',
                              inscricao: '07.123.456/0001-78',
                              cep: '98000000',
                              estado: 'Washington',
                              pais: 'Estados Unidos',
                              descricao: 'A nuvem para todos e todos os aspectos da vida digital.',
                    },
                    {
                              nome: 'Amazon',
                              email: 'contato@amazon.com',
                              inscricao: '09.987.654/0001-32',
                              cep: '98100000',
                              estado: 'Washington',
                              pais: 'Estados Unidos',
                              descricao: 'Tudo o que você precisa, entregue rapidamente à sua porta.',
                    },
                    {
                              nome: 'Tesla',
                              email: 'contato@tesla.com',
                              inscricao: '15.369.852/0001-54',
                              cep: '94025000',
                              estado: 'Califórnia',
                              pais: 'Estados Unidos',
                              descricao: 'Acelerando a transição do mundo para a energia sustentável.',
                    },
          ];

          const empresas = [] as Empresa[];

          Mockempresas.forEach(empresaData => {
                    const empresa = new Empresa(empresaData);

                    const vagaData = {
                              nome: 'Desenvolvedor HTML',
                              descricao: 'Precisamos de alguém que programe em HTML.',
                              criacao: new Date(),
                              competencias: [] as Competencia[],
                    };

                    const competenciasAleatorias = [];

                    for (let i = 0; i < 3; i++) {
                              const competenciaAleatoria = competencias[Math.floor(Math.random() * competencias.length)];
                              competenciasAleatorias.push(competenciaAleatoria);
                    }

                    vagaData.competencias = competenciasAleatorias;

                    const vaga = new Vaga(vagaData);
                    empresa.vagas.push(vaga);

                    empresas.push(empresa);


          });

          deletarChaveCadastro('empresas');

          empresas.forEach(empresa => {guardarCadastro('empresas', empresa);});
          
};
