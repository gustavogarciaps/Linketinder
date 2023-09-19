import { Competencia } from "../models/Competencia";
import { RepositorioCompetencias } from "../repositories/RepositorioCompetencias";

export const instanciarCompetencias = (): Competencia[] => {

  const repositorioCompetencias = new RepositorioCompetencias();

  repositorioCompetencias.adicionarCompetencia(new Competencia("Java"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Python"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("JavaScript"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("HTML"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("CSS"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("React"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Node.js"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Angular"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Vue.js"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("PHP"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Ruby"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("C#"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Swift"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Ruby on Rails"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("TypeScript"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Kotlin"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Go"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Rust"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Vue.js"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Scala"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Design de Interface"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("UX/UI Design"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Marketing Digital"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Gerenciamento de Projetos"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Análise de Dados"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Redes de Computadores"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Segurança da Informação"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Animação 3D"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Arquitetura de Software"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Fotografia"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Mídias Sociais"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("SEO (Otimização de Mecanismos de Busca)"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Marketing de Conteúdo"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Logística"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Finanças"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Recursos Humanos"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Gestão de Projetos"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Gestão de Pessoas"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Psicologia Organizacional"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Marketing de Eventos"));
  repositorioCompetencias.adicionarCompetencia(new Competencia("Discípulo do ACZG"));

  return repositorioCompetencias.listarCompetencias();
};
