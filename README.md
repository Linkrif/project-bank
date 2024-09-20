API para controle bancario.

Para configurar basta rodar:
docker run --hostname=project-bank --env=PATH=/opt/openjdk-17/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=JAVA_HOME=/opt/openjdk-17 --env=JAVA_VERSION=17-ea+14 --network=bridge -p 127.0.0.1:80:8080 --restart=no --runtime=runc -t -d project-bank

Acesse o endpoint para documentacao:
- [Swagger Ui](http://localhost:8080/swagger-ui/index.html)
  
foi implementado Banco H2 para os testes de integração
- [Banco H2](http://localhost:8080/h2-console)
- caminho do banco: jdbc:h2:mem:PROJECTBANK
- User sa 
- Password sa 
  
O banco é salvo em memoria, somente para testes.

Funcionalidades

Projetado para atender as principais movimentações de uma conta
  DEPÓSITO
  SAQUE
  TRANSFERÊNCIA

Em todas as transferências, o sistema alimenta as tabelas de histórico(seja um dos três citados acima).

### :bookmark_tabs: Fluxo e algumas tomadas de decições
https://miro.com/app/board/uXjVLfeMqNs=/?share_link_id=581401714076&shareablePresentation=1

Bibliotecas e conceitos aplicados no projeto :

Spring Data JPA

Spring Boot Web

Spring Boot Security - Geração de token OAuth JWT para controle das requisições, mantenho o usuario no payload pra uso nas seguintes requisições

Swagger UI - (Para documentar e testes de integração)

O projeto foi estruturado no padrão repository.
