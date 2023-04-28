### cs_20238
Repositório definido para a manutenção do controle de versão dos artefatos do projeto de construção de uma API Rest para:

armazenamento de imagens com um título e uma descrição atreladas, acessível de forma web para a distribuição dessas imagens, antes armazenadas localmente, entre plataformas diversas.

### Grupo
Esta API será construída pelos componentes do grupo 8:

|Matrícula|Nome|Usuário Git|
|---|---|---|
|202105026|Felipe Alves Leão de Araújo|[FelipeAlvesLeao](https://github.com/FelipeAlvesLeao)|
|202105031|Gabriel Guimarães Cordeiro Bispo|[gabrielgcb](https://github.com/gabrielgcb)|
|202108001|Robert Cardoso Cantares Martins|[Robert-Martins](https://github.com/Robert-Martins)|
|202105057|Yuan Andrade Calixto dos Santos|[YuanCalixto](https://github.com/YuanCalixto)|

### Requisitos Funcionais
1. RF001 - Autentificação.
2. RF002 - Ver todas as imagens enviadas pra database.
3. RF003 - (com autentificação) Enviar nova imagem com título e descrição.
3. RF004 - (com autentificação) Deletar imagem.
3. RF005 - (com autentificação) Mudar título ou descrição da imagem.

### Requisitos Não Funcionais
A interface do sistema deve ser intuítiva e web, o sistema comporta qualquer equipamento e tela capaz de acessar um browser de internet, as funções de deletar e mudar o título só funcionam caso a pessoa autenticada seja a pessoa que enviou a imagem.

### Regras de Negócio
1. RN01 - Imagens devem ter um limite de 11mb.
2. RN02 - Imagens não devem incluir conteúdo sexual, inflamatório ou ilegal.

<Adicionar outras regras, se existirem.>

### Tecnologia de _Front-end_
A tecnologia usada para fazer o front-end que acessa a API é a biblioteca React.JS
### Tecnologia de _Back-end_
A tecnlogia usada para fazer o servidor é a linguagem Java com a framework Spring Boot.
### Tecnologia de Persistência de Dados
A tecnologia usada para fazer a persistência de dados é a plataforma MongoDB Atlas, que oferece hospedagem e manuseamento de bancos de dados MongoDB.
### Local do _Deploy_
O deploy da API será feito na plataforma DigitalOcean.
### Cronograma de Desenvolvimento

|Iteração|Tarefa|Data Início|Data Fim|Responsável|Situação|
|---|---|---|---|---|---|
|1|Especificar História de Usuário 1|02/05/2023|03/05/2023|Yuan|Programada|
