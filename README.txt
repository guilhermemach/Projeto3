#Tarefa 1 e 2

- Estrutura de framework e principais libs/software
    
    Spring - Beans de serviço, DAO e Controle
    Spring MCV Rest - Controle e disposição dos webservices rest.
    Spring JDBC NamedQuery - estrutura de queries
    HSQL Database Embeeded - cria o banco de dados temporario durante o ambiente de testes e integração.
    Mockito - faz o mock das classes.
    Dbunit - faz a persistencia dos dados na base embeeded em ambiente de testes.
    Log4J - Logs de negocio e erro.
    Jackson - jackson-mapper-asl, biblioteca que faz o parser de objeto para xml/json e vice-versa.
    Maven - controle de versoes de libs e build.
    Tomcat 8.0 - servidor web
    IDE eclipse - IDE de desenvolvimento
    Firefox/RestClient - plugin firefox para envio de json com todos tipos de operação GET, POST, PUT e DELETE.
    JDK 1.7.0_80 - versão do java.


- Estrutura de pacotes
    
    /src/main/java  - Classes java
    /src/main/resources - xml do spring, spring MVC e carregador do HSQL (database embeeded)
    
    /src/main/test  - Classes java de teste unitário
    /src/main/resources - xml do spring, spring MVC, carregador do HSQL (database embeeded), xml de carregador de DBUnit (dados de preparação de  teste) 
  
- Projeto
    
    O projeto foi desenvolvido em duas partes:  front end (disposição dos webservices - Projeto ChallengeRestWebservice) e o back end (serviços com logica de negocio e DAO's operações com banco de dados - Projeto ChallengeService)
    
    * O projeto de infra/back end está como dependecia no pom.xml do front end (ChallengeRestWebservice) )


- ChallengeService
    
    Projeto de infra que contem toda a regra de negócio implementada em camada de 'Servico' e a persistência no formato 'DAO' com base de dados embeeded (HSQL). Além estão implementados todos testes unitários de negócio.
    Está disponivel comentário do tipo 'javadoc' nas classes e métodos.
    
    * A camada de CEP foi desenvolvida para questão de teste e integração do todo, apesar de na maioria de teste unitário ele foi mocado.
    
    * Existe consulta à tabela de municipio qual é compartilhada entre o CEP e ENDEREÇO. Portanto, há uma validação entre elas garantindo que não há incosistência do preenchimento dos dados.

- ChallengeRestWebservice
    
    Projeto que disponibiliza o endpoint para os serviços rest no formato spring MVC
    
    Rest EnderecoRS.java - CRUD de endereco (GET, POST, PUT e DELETE).
    Rest CepRS.java - busca de CEP (GET)
  
  
- Teste de Integração (consumo do xml/json via client)
  
    Para consumir os serviços é necessário usar um client, no caso utilizei 'Firefox/RestClient'.
    
    Segue abaixo o exemplo dos chamados:
    
    http://localhost:8080/ChallengeRestWebservice/rest/cep/11111111 (get endereço por CEP)
    
    http://localhost:8080/ChallengeRestWebservice - contexto da aplicação
    /rest - contexto do dispatcher do spring
    /cep/11111111 - definição no Rest CepRS.java como /cep/{cep}
    
    resposta - {"numeroCep":"11111111","municipio":{"descricao":"São Paulo","uf":"SP"},"rua":"Av paulista","bairro":"centro"}
  
    
    http://localhost:8080/ChallengeRestWebservice/rest/endereco/1 (get Endereco)
    
    resposta - {"id":1,"cep":"11111111","numero":1001,"bairro":"Centro","complemento":"Apto 308","rua":"Av paulista","municipio":{"descricao":"São Paulo","uf":"SP"}}
    
    
    - Remover Endereço basta mudar o metodo de envio para DELETE e passar o parametro a excluir assim como é para o get.
    - Adicionar Endereço basta passar a mesma resposta do 'get endereco' que seja adicionado um novo (novo ID) e com o metodo 'POST'.
    - Atualizar Endereço basta passar a mesma resposta do 'get endereco' com um ID do endereço na URL para update  e com o metodo 'PUT'.
    
    * Lembrando que é necessário usar um atributo de cabeçalho do envio (Content-Type = application/json) e selecionar o metodo correto de acordo com a operação (GET, POST, PUT e DELETE).
    * Para validação de request, inconsistencias de cadastro e parametro obrigatório faltando foi implementado o tratamento do BAD Request - 400 com a mensagem especifica.
    * Para CEP e ENDEREÇO não localizado por padrão devolvo objeto nulo (json nulo com resposta HTTP200).
