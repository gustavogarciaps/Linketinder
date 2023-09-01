
# LINKETINDER utilizando Groovy

O intuito desta aplicação é promover o match entre candidatos e empresas através da análise de competências. O Linketinder avalia as competências que a empresa está buscando e procura o candidato ideal para ela. 

No momento, as principais funcionalidades ainda não foram implementadas, porém temos um MVP da aplicação com as opções listadas a seguir.


## Funcionalidades

- Instanciar um Objeto da empresa ou candidato com seus principais campos
- Visualizar os candidatos e empresas cadastrados através do menu no terminal



## Melhorias

Eu tentei deixar a aplicação com o mais baixo acoplamento possível. Criei classes para o Gerenciamento dos Candidatos, Empresas e Competências. Essa estruturação tem o intuito de abastrair as funcionalidades futuras para essas classes, como por exemplo as ordenações e filtros. 

Para o futuro:

- Separar os candidatos que possuem x competências específicas.
- Sistema de match entre Candidatos e Empresas, que compara as competências.


