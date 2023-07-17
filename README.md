### cs_20238
Repositório definido para a manutenção do controle de versão dos artefatos do projeto de construção de uma API Rest para:

Armazenamento de imagens com um título e uma descrição atreladas, acessível de forma web para a distribuição dessas imagens, antes armazenadas localmente, entre plataformas diversas.

### Grupo
Esta API será construída pelos componentes do grupo 8:

|Matrícula|Nome|Usuário Git|
|---|---|---|
|202105026|Felipe Alves Leão de Araújo|[FelipeAlvesLeao](https://github.com/FelipeAlvesLeao)|
|202105031|Gabriel Guimarães Cordeiro Bispo|[gabrielgcb](https://github.com/gabrielgcb)|
|202108001|Robert Cardoso Cantares Martins|[Robert-Martins](https://github.com/Robert-Martins)|
|202105057|Yuan Andrade Calixto dos Santos|[YuanCalixto](https://github.com/YuanCalixto)|

### Requisitos Funcionais

<details><summary>Clique para ver mais</summary>

1. RF0001 - Cadastro de novos usuários.
2. RF0002 - Autentificação.
3. RF0003 - (com autentificação) Enviar nova imagem com título e descrição.
4. RF0004 - (com autentificação) Deletar imagem.
5. RF0005 - (com autentificação) Mudar título ou descrição da imagem.
6. RF0006 - Ver todas as imagens enviadas para o database filtradas e paginadas.
7. RF0007 - Baixar imagem.
8. RF0008 - Visualizar quantidade de visualizações e downloads de uma imagem

</details>

<br>

### Requisitos Não Funcionais

<details><summary>Clique para ver mais</summary>

1. RNF001 - A interface do sistema deve ser intuitiva e web
2. RNF002 - O sistema comporta qualquer equipamento e tela capaz de acessar um browser de internet

</details>

<br>

### Regras de Negócio
<details><summary>Clique para ver mais...</summary>

1. RN0001 - Imagens devem ter um limite de 11mb.
2. RN0002 - Imagens não devem incluir conteúdo sexual, inflamatório ou ilegal.
3. RN0003 - Imagens só podem ser inseridas, atualizadas ou deletadas por usuários autenticados.
4. RN0004 - Uma imagem só pode ser atualizada ou deletada pelo usuário que a inseriu

</details>

<br>

### Tecnologia de _Front-end_
A tecnologia usada para fazer o front-end que acessa a API é a biblioteca React.JS

<br>

### Tecnologia de _Back-end_
A tecnlogia usada para fazer o servidor é a linguagem Java com a framework Spring Boot.

<br>

### Tecnologia de Persistência de Dados
A tecnologia usada para fazer a persistência de dados é a plataforma MongoDB Atlas, que oferece hospedagem e manuseamento de bancos de dados MongoDB. O mapeamento de Objetos para Documentos será realizado utilizando o Spring Data MongoDB.

<br>

### Local do _Deploy_
O deploy da API será feito na plataforma DigitalOcean.

<br>

### Cronograma de Desenvolvimento

| Iteração | Tarefa | Data Início | Data Fim | Situação |
| --- |---|---|---|---|
| 1 | Especificar Escopo do Projeto e Requisitos | 17/04/2023 | 05/05/2023 | Concluída |
| 2 | Definir Entidades e Estruturar Aplicações | 06/05/2023 | 19/05/2023 | Concluída |
| 3 | CRUD de Usuários e Autenticação | 20/05/2023 | 02/06/2023 | Concluída |
| 4 | CRUD de Imagens | 03/06/2023 | 16/06/2023 | Concluída |
| 5 | Filtragem e Listagem de Imagens | 17/06/2023 | 30/06/2023 | Parcialmente Concluída |
| 6 | Visualização e Download de Imagens, Testes Automatizados | 01/07/2023 | 21/07/2023 | Em Andamento |
| 7 | Deploy Automatizado | 22/07/2023 | 11/08/2023 | Programada |
