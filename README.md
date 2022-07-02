# CineCriticasAPI

## API geral de regras de negócio para o coding challenge da Let's Code

### Status: Versão 1.0 finalizada!

## Sobre

Desenvolvedor: Rodrigo Ricci de Paula Mosken

O projeto foi desenvolvido utilizando minha concepção de Clean Arch,
usando seu famoso "tripé" Adapters, Usecases, Entities.
A API concentra as funcionalidades principais do desafio, como buscar as informações
de filmes, utilizando a API externa OMDB, inserir comentários e avaliações relacionados aos filmes, e etc.
Utiliza um banco de dados mySQL, a AuthorizationAPI para login e validação dos tokens, e a OMDBAPI para os dados dos
filmes.

Possui dez endpoints, que serão descritos no próximo tópico!

## Endpoints

1) GET cinecriticas/v1/search/movie
    - Headers :
        1) String Authorization (token em formato JWT);
    - Parameters:
        1) String title (Opcional*) (título do filme pesquisado)
        2) String id (Opcional*) (id do filme pesquisado)
        3) String year (Opcional) (ano do filme pesquisado)

        * É necessário passar pelo menos um dos dois parametros (title ou id), o "year" é opcional e utilizado em
          conjunto com o "title" para filtrar o filme.
    - Retorno:

        1) codigo 200 OK:
     ````
       {
    "comments": [
        {
            "id": 1,
            "username": "romosken",
            "text": "aaaaaaaaaaa!",
            "likes": 0,
            "dislikes": 0,
            "repeated": false,
            "movie_id": "tt1099212",
            "comment_reference": 0,
            "comment_reply": 0
        }
    ],
    "ratings": [
        {
            "id": 1,
            "username": "romosken",
            "rating": 0,
            "movie_id": "tt1099212"
        }
    ],
    "movie_data": {
        "id": "tt1099212",
        "title": "Twilight",
        "year": "2008",
        "rated": "PG-13",
        "released": "21 Nov 2008",
        "runtime": "122 min",
        "genre": "Drama, Fantasy, Romance",
        "director": "Catherine Hardwicke",
        "writer": "Melissa Rosenberg, Stephenie Meyer",
        "actors": "Kristen Stewart, Robert Pattinson, Billy Burke",
        "plot": "When Bella Swan moves to a small town in the Pacific Northwest, she falls in love with Edward Cullen, a mysterious classmate who reveals himself to be a 108-year-old vampire.",
        "language": "English",
        "country": "United States",
        "awards": "32 wins & 16 nominations",
        "poster": "https://m.media-amazon.com/images/M/MV5BMTQ2NzUxMTAxN15BMl5BanBnXkFtZTcwMzEyMTIwMg@@._V1_SX300.jpg",
        "ratings": [
            {
                "source": "Internet Movie Database",
                "value": "5.3/10"
            },
            {
                "source": "Rotten Tomatoes",
                "value": "49%"
            },
            {
                "source": "Metacritic",
                "value": "56/100"
            }
        ],
        "metascore": "56",
        "type": "movie",
        "dvd": "21 Mar 2009",
        "production": "N/A",
        "website": "N/A",
        "imdb_rating": "5.3",
        "imdb_votes": "456,067",
        "box_office": "$193,962,473"
     }

     }
     ````

    2) codigo 404 NOT FOUND:

     ````
     {
       "timestamp": "01-07-2022 11:48:38",
       "code": 404,
       "status": "NOT_FOUND",
       "message": "Movie not found!"
     }
     ````
    2) codigo 400 BAD REQUEST:

     ````
     {
       "timestamp": "01-07-2022 11:48:38",
       "code": 400,
       "status": "BAD_REQUEST",
       "message": "Token expired or invalid!"
     }
     ````

2) POST cinecriticas/v1/user/signup
    - Headers :
        1) String Authorization (token em formato JWT);
    - body:
        ````
         {
           "username":"romosken5",
           "password":"123456",
           "email":"rodrigo@hotmail4.com"
          }  
      ````
    - Retorno:

        1) codigo 200 OK:
     ````
     {
     "username": "romosken5",
     "email": "rodrigo@hotmail4.com",
     "xp": 0,
     "role": "LEITOR"
     }
     ````

    2) codigo 400 BAD REQUEST:

     ````
     {
       "timestamp": "01-07-2022 11:48:38",
       "code": 400,
       "status": "BAD_REQUEST",
       "message": "Token expired or invalid!"
     }
     ````
   3) codigo 409 CONFLICT:

     ````
     {
       "timestamp": "02-07-2022 12:24:24",
       "code": 409,
       "status": "CONFLICT",
       "message": "Username already exists!"
     }
     ````

4) POST cinecriticas/v1/user/login
    - Headers :
        1) String username (id do usuário);
        2) String password (senha do usuário).
    - Retorno:
        1) codigo 200 OK:
      ````
      {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOiJGcmkgSnVsIDAxIDIxOjQ0OjM1IEJSVCAyMDIyIiwiaWF0IjoiRnJpIEp1bCAwMSAyMDo0NDozNSBCUlQgMjAyMiIsInN1YiI6InJvbW9za2VuIiwianRpIjoiOTQ1YzIwYzYtNGRmMC00NjFjLWJkZTctNjFhNzg1MGRkYzBiIn0.A4LESSHwRcznCQ4yFrOBMB40KkKzRP3HzaSIIRecraY"
      }
      ````
        2) codigo 401 Unauthorized:
      ````
      {
        "timestamp": "01-07-2022 11:45:57",
        "code": 401,
        "status": "UNAUTHORIZED",
        "message": "[401 ] during [POST] to [http://localhost:8080/authorization/v1/login] [IAuthorizationApi#logIn(String,String)]: [{\"timestamp\":\"01-07-2022 11:54:22\",\"code\":401,\"status\":\"UNAUTHORIZED\",\"message\":\"Username/email or password invalid!\"}]"
      }
        ````   

5) POST cinecriticas/v1/rating/add
    - Headers :
        1) String Authorization (token em formato JWT);
    - body:
        ````
         {
           "username":"romosken",
           "movie_id":"tt1099212",
           "rating":100
          }  
      ````   
        - Retorno:

            1) codigo 201 CREATED:
      ````
      {
        "response": "Success inserting new rating!"
      }
      ````

    2) codigo 400 BAD REQUEST:

     ````
     {
       "timestamp": "01-07-2022 11:48:38",
       "code": 400,
       "status": "BAD_REQUEST",
       "message": "Token expired or invalid!"
     }
     ````
   * Caso a avaliação já exista, o usuario nao receberá mais pontos por isso, apenas mudará a nota ao filme
6) GET cinecriticas/v1/comment/add
    - Headers :
        1) String Authorization (token em formato JWT);
    - body:
       ````
        {
           "username":"romosken",
           "movie_id":"tt1099212",
           "text":"aaaaaaaaaaa!",
           "comment_reference":0, (Opcional)
           "comment_reply":0 (Opcional)
         }  
      ````   
        * Para adicionar resposta ou referencia a um comentário, basta inserir o id do comentario referenciado,
          a frente do parametro desejado!

        - Retorno:

            1) codigo 201 CREATED:
      ````
      
        {
          "id": 12,
          "username": "romosken",
          "text": "aaaaaaaaaaaa!",
          "likes": 0,
          "dislikes": 0,
          "repeated": false,
          "movie_id": "tt0848228",
          "comment_reference": 0,
          "comment_reply": 0
      }
      
      ````

    2) codigo 400 BAD REQUEST:
       ````
       {
        "timestamp": "02-07-2022 12:07:28",
        "code": 400,
        "status": "BAD_REQUEST",
        "message": "Token invalid!"
       }
       ````   

7) GET cinecriticas/v1/delete/{id}
    - Headers :
        1) String Authorization (token em formato JWT);
    - Path Variables:
        1) int id (id do comentário que deseja deletar)
   - Retorno:

       1) codigo 200 OK:
    ````
    {
      "response": "The comment was successfully deleted!"
    }
    ````
    2) codigo 400 BAD REQUEST:
       ````
       {
        "timestamp": "02-07-2022 12:07:28",
        "code": 400,
        "status": "BAD_REQUEST",
        "message": "Token invalid!"
       }
       ````   
    3) codigo 404 NOT FOUND:
       ````
       {
        "timestamp": "02-07-2022 12:08:20",
        "code": 404,
        "status": "NOT_FOUND",
        "message": "The comment does not exists!"
       }
       ````
8) GET cinecriticas/v1/like/{id}
    - Headers :
        1) String Authorization (token em formato JWT);
    - Path Variables:
        1) int id (id do comentário que deseja dar like)
   - Retorno:

       1) codigo 200 OK:
    ````
    {
      "response": "Success adding/removing like to comment!"
    }
    ````
    2) codigo 400 BAD REQUEST:
       ````
       {
        "timestamp": "02-07-2022 12:07:28",
        "code": 400,
        "status": "BAD_REQUEST",
        "message": "Token invalid!"
       }
       ````   
    3) codigo 404 NOT FOUND:
       ````
       {
        "timestamp": "02-07-2022 12:08:20",
        "code": 404,
        "status": "NOT_FOUND",
        "message": "The comment referenced does not exist!"
       }
       ````
9) GET cinecriticas/v1/dislike/{id}
    - Headers :
        1) String Authorization (token em formato JWT);
    - Path Variables:
        1) int id (id do comentário que deseja dar dislike)
   - Retorno:

       1) codigo 200 OK:
    ````
    {
      "response": "Success adding/removing dislike to comment!"
    }
    ````
    2) codigo 400 BAD REQUEST:
       ````
       {
        "timestamp": "02-07-2022 12:07:28",
        "code": 400,
        "status": "BAD_REQUEST",
        "message": "Token invalid!"
       }
       ````   
    3) codigo 404 NOT FOUND:
       ````
       {
        "timestamp": "02-07-2022 12:08:20",
        "code": 404,
        "status": "NOT_FOUND",
        "message": "The comment referenced does not exist!"
       }
       ````
10) GET cinecriticas/v1/markasrepeated/{id}
     - Headers :
         1) String Authorization (token em formato JWT);
     - Path Variables:
         1) int id (id do comentário que deseja marcar como repetido)
     - Retorno:

         1) codigo 200 OK:
     ````
     {
       "response": "Success adding/removing repeated to comment!"
     }
     ````
     2) codigo 400 BAD REQUEST:
        ````
        {
         "timestamp": "02-07-2022 12:07:28",
         "code": 400,
         "status": "BAD_REQUEST",
         "message": "Token invalid!"
        }
        ````   
     3) codigo 404 NOT FOUND:
        ````
        {
         "timestamp": "02-07-2022 12:08:20",
         "code": 404,
         "status": "NOT_FOUND",
         "message": "The comment referenced does not exist!"
        }
        ````
11) GET cinecriticas/v1/turnmoderator/{username}
    - Headers :
        1) String Authorization (token em formato JWT);
    - Path Variables:
        1) String username (username do usuário que deseja tornar moderador)
    - Retorno:

      1) codigo 200 OK:
     ````
     {
    "response": "Success upgrading user!"
     }
     ````
      2) codigo 400 BAD REQUEST:
     ````
       {
        "timestamp": "02-07-2022 12:07:28",
        "code": 400,
        "status": "BAD_REQUEST",
        "message": "Token invalid!"
       }
       ````   
      3) codigo 404 NOT FOUND:
     ````
       {
        "timestamp": "02-07-2022 12:08:20",
        "code": 404,
        "status": "NOT_FOUND",
        "message": "The user does not exist!"
       }
       ````

## Tecnologias Utilizadas

- JDK 11
- Spring
- Maven
- MySQL
- IntelliJ IDE

## Rodando o Projeto

- Para executar o projeto é necessário ter o banco de dados MySQL rodando, abaixo segue a query para criação do banco:
  (O arquivo contendo o código abaixo pode ser encontrado na pasta "documents" na raiz do projeto com o nome "
  createdatabase.sql")

 ```
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinecriticas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinecriticas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinecriticas` ;
USE `cinecriticas` ;

-- -----------------------------------------------------
-- Table `cinecriticas`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Role` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`User` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `xp` INT NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_Users_Roles_idx` (`role_name` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`role_name`)
    REFERENCES `cinecriticas`.`Role` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `rating` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Rating_User_idx` (`username` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_Rating_User`
    FOREIGN KEY (`username`)
    REFERENCES `cinecriticas`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  `likes` INT NULL,
  `dislikes` INT NULL,
  `comment_reference` INT NULL,
  `comment_reply` INT NULL,
  `repeated` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Comment_User_idx` (`username` ASC) VISIBLE,
  INDEX `fk_Comment_Reference_idx` (`comment_reference` ASC) VISIBLE,
  INDEX `fk_Comment_Reply_idx` (`comment_reply` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_User`
    FOREIGN KEY (`username`)
    REFERENCES `cinecriticas`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Reference`
    FOREIGN KEY (`comment_reference`)
    REFERENCES `cinecriticas`.`Comment` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Reply`
    FOREIGN KEY (`comment_reply`)
    REFERENCES `cinecriticas`.`Comment` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `cinecriticas`.`role`
(`name`)
VALUES
("LEITOR"),
("BASICO"),
("AVANCADO"),
("MODERADOR");
```

- Após criação do banco alterar o parametro spring.datasourse.url no arquivo application.yml
  com o endereço do seu banco de dados, e os outros parametros conforme seu ambiente/vontade.


- Após isso você tem tudo que é necessário para executar a API! Os testes podem ser realizados via Postman ou similares
  ou pela API CineCriticas, que utiliza esses dois endpoints internamente!


- A Collection do Postman se encontra na pasta "documents" na raiz do projeto, com o nome "Desafio Let's
  Code.postman_collection.json"!


- O diagrama do modelo do banco de dados se encontra na pasta "documents" na raiz do projeto, com o nome "
  databaseEERModel.png"
  e em formato do MySQL Workbench com o nome "model.mwd"
