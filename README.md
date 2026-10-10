# Entre laços & nós

Um lugar para guardar e compartilhar **receitas** e **projetos** de artesanato. Você cria sua conta, publica o que faz e vê o que os outros publicaram.

## O que dá para fazer

- Criar conta, entrar e editar o perfil
- Cadastrar, editar e excluir receitas
- Cadastrar, editar e excluir projetos, ligando cada um a uma receita, se quiser
- Pesquisar no feed
- Escolher entre tema claro e escuro

Qualquer pessoa logada pode editar uma postagem, mas só quem a criou pode excluí-la.

## Telas

- **Login e criar conta:** a porta de entrada.
- **Tela inicial:** o feed, com as abas *home*, *receitas*, *projetos* e *configurações*.
- **Formulários:** um para criar e outro para editar cada coisa (receita, projeto e perfil).

## Para rodar

Você precisa de Java 17 e MySQL.

1. Crie o banco: `CREATE DATABASE entrelacos_web;`
2. Coloque seu usuário e senha do MySQL em `src/main/resources/application.properties`.
3. Rode `./mvnw spring-boot:run` (ou use o *Run* do NetBeans).
4. Abra `http://localhost:8080` no navegador.

As tabelas são criadas sozinhas na primeira execução.

## Feito com

Java 17, Spring Boot, MySQL, Thymeleaf, jQuery, HTML e CSS.
