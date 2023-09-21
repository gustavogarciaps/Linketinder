import { Candidato } from "../models/Candidato";
import { recuperarCadastro } from "../services/ServicoArmazenamento";

export const carregarCandidatos = () => {

          const candidatosJSON = recuperarCadastro('candidatos') as Candidato[];
          const candidatos = [] as Candidato[];

          candidatosJSON.forEach(candidato => {
                    candidatos.push(new Candidato(candidato));
          });

          candidatos.forEach(candidato => {

          });
}

