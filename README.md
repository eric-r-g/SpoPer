# SpoPer

Trabalho Final da cadeira de Fundamentos de Bancos de Dados da UFC.

Consiste num programa Java e um banco de dados PostgreSQL.

## Estrutura do repositório

### scripts/criacao_banco

Aqui se encontram os scripts de criação do banco de dados, o scripts devem ser executados na seguinte ordem:

1. **Script_SpotPerUser.sql** - Criar o usuário SpotPer.
2. **Script_CreateDB.sql** - Criar o banco de dados SpotPer.
3. **Script_SpotPer.sql** - Criar as tabelas do banco de dados.
4. **Script_Povoar_SpotPer.sql** - Povoar o banco de dados.

Um detalhe importante é que criar os tablespaces no Postgres costuma ser trabalhoso, pois é necessário criar pastas manualmente e dar permissão de leitura e escrita sobre elas para o banco, caso não consiga, remove as partes que controlam os tablespaces em **Script_SpotPer.sql** e **Script_Povoar_SpotPer.sql**.

### scripts/funcoes

Aqui se encontra apenas uma função, que foi pedida nas especificações do trabalho.

### scripts/visoes

Aqui se encontra a visão **playlist_qtd_albuns.sql**, que foi pedida nas especificações do trabalho, além de outras visões que são utilizadas no programa Java.

### scripts/gatilhos

Aqui se encontram os gatilhos que implementam as restrições pedidas nas especificações do trabalho, uma quarta restrição se encontra no próprio script de criação do banco de dados.

### scripts/consultas_banco

Aqui se encontram as consultas que devem ser feitas no programa Java, como pedido nas especificações do trabalho.

### SpotPer

Nessa pasta se encontra o nosso projeto java, com uma classe database, que contém os acessos ao banco de dados, e uma classe Main, que contém o fluxo e controle do programa.

### drivers

Por ultimo, temos o driver de acesso do postgresql, para ser utilizado como biblioteca externa no programa Java.