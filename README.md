# _Project Bank_

Para iniciar o projeto basta rodar na pasta raiz do projeto:

```docker
docker-compose -f .\docker-compose\docker-compose.yml up 
```
Em casos de erro na inicialização do banco de dados
Rodar o script de init.sql direto na instancia ou recriar os arquivos './sh' que estão na pasta docker-compose/database, aparentemente quando o Intellij pega o projeto, vem num enconding errado.

Acesse o endpoint para documentação:

- [Swagger Ui](http://localhost:8080/swagger-ui/index.html)

  
Funcionalidades

Projetado para atender as principais movimentações de uma conta

> DEPÓSITO
> 
> SAQUE
> 
> TRANSFERÊNCIA
>


Em todas as transferências, o sistema alimenta as tabelas de histórico(seja um dos três citados acima).

### :bookmark_tabs: Fluxo e algumas tomadas de decições
- [Miro](https://miro.com/app/board/uXjVLfeMqNs=/?share_link_id=581401714076&shareablePresentation=1)


### **_Bibliotecas e conceitos aplicados no projeto_ :**

1. **Spring Data JPA**
2. **Spring Boot Web**
3. **Spring Boot Security - Geração de token OAuth JWT para controle das requisições, mantenho o usuario no payload pra uso nas seguintes requisições**
4. **Swagger UI - (Para documentar e testes de integração)**
5. **O projeto foi estruturado no padrão repository.**
6. **Microsoft SQL Server**
7. **Clean Code** 
8. **Docker Compose**


_**Para testes foi utilizado as seguintes dependencias**_
 
1. **JUnit**
2. **Mockito**
3. **Banco H2**