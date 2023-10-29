## Autor

- [@gustavogarciaps](https://github.com/gustavogarciaps)

# Linketinder: Conectando Talentos e empresas

Linketinder é uma plataforma que combina a abordagem profissional do LinkedIn com a dinâmica de conexão do Tinder. Aqui, candidatos e empresas podem criar perfis, explorar oportunidades e se conectar de maneira eficaz. É o futuro do recrutamento e networking profissional.

## Funcionalidades

- [ ] Cadastro e Autenticação de Usuários.
- [x] Perfis personalizados para Empresas e Candidatos.
- [x] Visualização de Candidatos de forma anônima. "Recrutamento às cegas".
- [x] Visualização de Vagas de Empresas anômimas.
- [ ] Sistema de match. O candidato pode curtir uma vaga e uma empresa pode curtir o candidato.
- [ ] Cadastro e manutenção de vagas e competências.
- [x] Dashboard para visualização da relação de candidatos por competência.

## Stack utilizada

**Front-end:** HTML, CSS, TS e Boostrap. Além disso, webpack para gerenciar.

**Back-end:** Groovy

**Banco de Dados:** Postgres

## Instalação

- [Sdkman (Gerenciador de Kit de Desenvolvimento de Software)](https://sdkman.io/)

```bash
  $ curl -s get.sdkman.io | bash
  $ source "$HOME/.sdkman/bin/sdkman-init.sh"
```

- Java

```bash
  $ sdk install java 17.0.8
```
- Groovy

```bash
  $ sdk install groovy 4.0.15
```
- Gradle

```bash
  $ sdk install gradle 8.3
```
 - Postgres
```bash
  $ 
```
Executando a aplicação frontend:

```bash
  cd frontend
  npm install
  npm run build
  npm start
```
A partir desses comandos, a aplicação pode ser acessível em: http://localhost:9000/

Executando a aplicação backend:

```bash
  cd backend
  gradle build
  gradle run
```

## Modelo Lógico do Banco de Dados

O modelo foi criado através do site https://dbdiagram.io/ e retrata a lógica de relacionamento das tabelas dentro do Banco de Dados.

![MER](https://raw.githubusercontent.com/gustavogarciaps/Linketinder/ab979c68b8f7fe96e07f0e1c35c5cb2d4b7743ed/docs/mer.svg)

### Mais informações:

- [MER hospedado no site](https://dbdiagram.io/d/Linketinder-65172b5fffbf5169f0c2c134)

- [Script do Banco de Dados](https://github.com/gustavogarciaps/Linketinder/blob/master/docs/script.sql)

## Lógica do Match

Para deixar o match (correspondências entre candidatos e empresas) dinâmico dentro do Banco de Dados, foi necessário implementar um gatilho para monitorar as tabelas. Esse gatilho (Trigger) será acionado todas as vezes que houver inserções de dados nas tabelas ```candidatos_vagas``` e ```empresas_candidatos```. Essas tabelas são responsáveis pela lógica de curtida de um candidato com uma vaga e de uma empresa com um candidato.

No Postegres, eu preciso criar uma função que será executada quando o Trigger for acionado. Essa função retorna o valor do Trigger. Depois de criar essa função, eu preciso associar ela a tabela que será responsável por acionar ela.


Script da função do meu Trigger que monitora a tabela ```candidatos_vagas```.

```SQL
CREATE OR REPLACE FUNCTION criar_correspondencia_candidato_vaga()
RETURNS TRIGGER AS $$
DECLARE
  empresas_candidatos_id INTEGER;
BEGIN
  IF NEW.curtida = TRUE 
  THEN
    SELECT ec.id INTO empresas_candidatos_id
    FROM empresas_candidatos ec
    WHERE ec.candidatos_id = NEW.candidatos_id
      AND ec.empresas_id = (
      SELECT empresas_id FROM vagas WHERE id = NEW.vagas_id
    )
      AND ec.curtida = TRUE;

    IF empresa_candidato_id IS NOT NULL THEN
      INSERT INTO correspondencias (empresas_candidatos_id, candidatos_vagas_id)
      VALUES (empresas_candidatos_id, NEW.id);
    END IF;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;
```

Nesse script, foi declarado a variável ```empresas_candidatos_id``` para armazenar o id da empresa responsável pela vaga. Para obtermos esse id, foi necessário realizar uma consulta SQL. Antes de todo o esforço, verificamos se o candidato curtiu a vaga (```NEW.curtida = TRUE```). Se sim (```THEN```), atribuímos o valor da consulta à variável ```empresas_candidatos_id``` (```SELECT ec.id INTO empresas_candidatos_id```) . Nesse caso, a consulta está selecionando o id da tabela ```empresas_candidatos```, onde o campo ```candidatos_id``` tenha o mesmo valor que o registro que havia sido inserido. Se tiver, a função faz uma inserção na tabela ``` correspondências``` com o valor do id da tabela ```empresas_candidatos``` e ```candidatos_vagas```.

O Trigger para a tabela ```empresas_candidatos``` segue quase o mesmo raciocínio, com a diferença que eu não preciso criar uma variável para armazenar o id do candidato, pois eu consigo consultar direto na tabela ```empresas_candidatos```.

## Refatoração do Código seguindo Clean Code

Houve a avaliação da estrutura do código e a renomeação de variáveis que estavam com uma nomenclatura genérica. Foi refatorado deixando com nomes mais expressivos e declarativos. Além disso, foi descartado algumas Classes que não faziam mais sentido no contexto da aplicação. Essas classes estavam deixando o código com uma intepretação mais complexa.

O maior desafio dessa refatoração, é a análise autocrítica, pois às vezes não é possível identificar uma variável ou função que está ambígua ou com a nomenclatura sem sentido. Isso se deve pois quando você cria a lógica da aplicação, tudo parece fazer muito sentido. Entretanto, quando você analisa de maneira externa, sem nenhuma paixão no código, vai percebendo MUITOS detalhes que poderiam ser melhorados. Porém, como é o primeiro Code Review que eu faço, ainda não consegui achar tantos pontos de melhoria. 


# Refatoração seguindo os princípios SOLID (ou pelo menos tentando)

No backend da aplicação, foi refatorado todas as classes, abstraindo os métodos para exercer apenas uma única função. A parte mais significativa que eu refatorei foi o input dos formulários. Criei uma classe para controlar tudo que se refere a entrada e saída dos dados. Como exemplo, o antes e depois.

Antes:
```groovy
    static void carregar() {

        vagas = VagaDAO.read()

        println("Vagas Cadastradas:")
        println("-" * 30);
        println("|id" + ("\t" * 2) + "|" + "Título" + ("\t" * 6) + "Empresa" + ("\t" * 4))

        vagas.each { v ->
            println("Vaga:")
            println("|" + v.getId() + ("\t" * 2) + "|" + v.getTitulo() + ("\t" * 6) + "|" + v.getEmpresa().getId() + ("\t" * 4))
            println("Competências:")

            v.getCompetencias().getCompetencia().each { competencia ->
                println("|" + competencia.getId() + ("\t" * 2) + "|" + competencia.getNome() + ("\t" * 4))
            }
        }
    }
```

Depois: ([conferir métodos]([ela](https://github.com/gustavogarciaps/Linketinder/blob/K2-T1/backend/src/main/groovy/utils/InputHelper.groovy)))
```groovy
    static void loadJobs(JobsDAO jobsDAO) {

        println("Vagas Cadastradas:")
        InputHelper.printDivider(80)

        jobsDAO.findAll().each {
            job ->
                InputHelper.printColumns(["id", "titulo", "empresa"])
                InputHelper.printColumns([job.getId(), job.getTitle(), job.getCompany().getId()])

                println("Competências Requisitadas")
                InputHelper.printColumns(["id", "nome"])

                jobsDAO.findAll(job).getSkills().each { it ->
                    InputHelper.printColumns([it.getId(), it.getName()])
                }
                InputHelper.printDivider(80)
        }
    }
```

Além disso, algumas conversões ou formatações de data que eu anexava à classe específica, eu criei uma Classe apenas para [ela](https://github.com/gustavogarciaps/Linketinder/blob/K2-T1/backend/src/main/groovy/utils/DateTimeHelper.groovy).

As demais alterações foram mais simplórias, como renomeação das classes para a língua inglesa, a criação de Exceções e o tratamento delas, e a utilização de annotations do groovy (@Canonical) para criação dos getters e setters.
