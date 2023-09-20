import { Vaga } from "../models/Vaga";
import { RepositorioVagas } from "../repositories/RepositorioVagas";
import { Competencia } from "../models/Competencia";

export const instanciarVagas = (): Vaga[] => {

          const repositorioVagas = new RepositorioVagas();
          const vaga1 = new Vaga(
                    "Desenvolvedor Full Stack",
                    "Requisitos necessários: Graduação em curso de nível superior na área de Tecnologia da Informação, ou conclusão de qualquer curso de nível superior acompanhado de certificado de curso de pós-graduação (especialização, mestrado ou doutorado) na área de Tecnologia",
                    new Date(),
          );

          vaga1.competencias.adicionarCompetencia(new Competencia("Java"));
          vaga1.competencias.adicionarCompetencia(new Competencia("JavaScript"));
          vaga1.competencias.adicionarCompetencia(new Competencia("Angular"));
          vaga1.competencias.adicionarCompetencia(new Competencia("React"));

          repositorioVagas.adicionarVaga(vaga1);

          const vaga2 = new Vaga(
                    "Desenvolvedor Frontend",
                    "Requisitos necessários: Graduação em curso de nível superior na área de Tecnologia da Informação, ou conclusão de qualquer curso de nível superior acompanhado de certificado de curso de pós-graduação (especialização, mestrado ou doutorado) na área de Tecnologia",
                    new Date(),
          );

          vaga2.competencias.adicionarCompetencia(new Competencia("Java"));
          vaga2.competencias.adicionarCompetencia(new Competencia("JavaScript"));
          vaga2.competencias.adicionarCompetencia(new Competencia("Angular"));
          vaga2.competencias.adicionarCompetencia(new Competencia("React"));
          vaga2.competencias.adicionarCompetencia(new Competencia("HTML"));
          vaga2.competencias.adicionarCompetencia(new Competencia("CSS"));
          vaga2.competencias.adicionarCompetencia(new Competencia("Bootstrap"));

          repositorioVagas.adicionarVaga(vaga2);

          return repositorioVagas.listarVaga();
}