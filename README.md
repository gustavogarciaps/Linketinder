
# Linketinder: Conectando Talentos e empresas

Linketinder é uma plataforma que combina a abordagem profissional do LinkedIn com a dinâmica de conexão do Tinder. Aqui, candidatos e empresas podem criar perfis, explorar oportunidades e se conectar de maneira eficaz. É o futuro do recrutamento e networking profissional.

## Funcionalidades

- Cadastro e Autenticação de Usuários.
- Perfis personalizados para Empresas e Candidatos.
- Visualização de Candidatos de forma anônima. "Recrutamento às cegas".
- Visualização de Vagas de Empresas anômimas.
- Sistema de match. O candidato pode curtir uma vaga e uma empresa pode curtir o candidato.
- Cadastro e manutenção de vagas e competências.
- Dashboard para visualização da relação de candidatos por competência.
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
  $ sdk install java <version>
```
- Groovy

```bash
  $ sdk install groovy <version>
```
- Gradle

```bash
  $ sdk install gradle <version>
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
  gradle start
```

## Modelo Lógico do Banco de Dados

O modelo foi criado através do site https://dbdiagram.io/ e retrata a lógica de relacionamento das tabelas dentro do Banco de Dados.

![MER](https://raw.githubusercontent.com/gustavogarciaps/Linketinder/ab979c68b8f7fe96e07f0e1c35c5cb2d4b7743ed/docs/mer.svg)

### Mais informações:

- [MER hospedado no site](https://dbdiagram.io/d/Linketinder-65172b5fffbf5169f0c2c134)

- [Script do Banco de Dados](https://github.com/gustavogarciaps/Linketinder/blob/master/docs/script.sql)

