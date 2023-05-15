### Definição de Entidades
##

As entidades da aplicação foram definidas com base nos requisitos e histórias de usuário.

Os atributos das entidades foram definidos em conformidade com as histórias vinculadas abaixo.

| ID da História de Usuário | Entidade Definida | Explicação |
| ------ | ------ | ------ |
| HU0001, HU0002 | User | Usuário para permitir acesso ao ambiente autenticado |
| HU0003, HU0004, HU0005, HU0006, HU0007, HU0008 | Image | Entidade referente aos posts do sistema |
| HU0003, HU0005, HU0006 | Tag | Entidade referente à classificação das imagens para melhor filtragem e categorização |
| HU0001, HU0003, HU0007 | Media | Foi necessário separar as informações de um arquivo de `Image` pois o usuário também irá possui uma imagem de perfil |