## Project Bank

Para iniciar o projeto basta rodar na pasta raiz do projeto:

```docker
docker-compose -f .\docker-compose\docker-compose.yml up 
```

Acesse o endpoint para documentacao:
- [Swagger Ui](http://localhost:8080/swagger-ui/index.html)

  
Funcionalidades

Projetado para atender as principais movimentações de uma conta
> DEPÓSITO
> SAQUE
> TRANSFERÊNCIA


Em todas as transferências, o sistema alimenta as tabelas de histórico(seja um dos três citados acima).

### :bookmark_tabs: Fluxo e algumas tomadas de decições
https://miro.com/app/board/uXjVLfeMqNs=/?share_link_id=581401714076&shareablePresentation=1

Bibliotecas e conceitos aplicados no projeto :

Spring Data JPA

Spring Boot Web

Spring Boot Security - Geração de token OAuth JWT para controle das requisições, mantenho o usuario no payload pra uso nas seguintes requisições

Swagger UI - (Para documentar e testes de integração)

O projeto foi estruturado no padrão repository.

Microsoft SQL Server
