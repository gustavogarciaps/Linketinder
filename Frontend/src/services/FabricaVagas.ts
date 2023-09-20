import { Vaga } from "../models/Vaga";
import { RepositorioVagas } from "../repositories/RepositorioVagas";
import { Competencia } from "../models/Competencia";

export const instanciarVagas = (): Vaga[] => {

          const repositorioVagas = new RepositorioVagas();
          const vagaTeste = new Vaga(
                    "Desenvolvedor Full Stack",
                    "Requisitos necessários: Graduação em curso de nível superior na área de Tecnologia da Informação, ou conclusão de qualquer curso de nível superior acompanhado de certificado de curso de pós-graduação (especialização, mestrado ou doutorado) na área de Tecnologia",
                    new Date(),
          );

          vagaTeste.competencias.adicionarCompetencia(new Competencia("Java"));
          vagaTeste.competencias.adicionarCompetencia(new Competencia("JavaScript"));


          repositorioVagas.adicionarVaga(vagaTeste);

          return repositorioVagas.listarVaga();
}