package com.mosken.rodrigo.letscode.challenge.cinecriticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CinecriticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinecriticasApplication.class, args);
	}


	//TODO: getComentarios e getAvaliações para todos os usuarios, talvez junto das infos dos filmes
	//TODO: Criptografar senhas
	//TODO: MOderador transformar em outro moderador
	//TODO: Logs na aplicação inteira
	//TODO: aplicar a variação de acesso por perfil
	//TODO: aplicação de segurança token e autorização
	//TODO: criar login
	//TODO: criar autenticação de cada endpoint, deixando apenas login sem autenticação
}
