package org.test;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.stream.Stream;

import org.controllers.EmailController;
import org.entities.AppRole;
import org.entities.Carnet;
import org.entities.Compte;
import org.entities.Contact;
import org.repositories.CarnetRepository;
import org.repositories.CompteRepository;
import org.service.AccountService;
import org.service.EmailService;
import org.service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan("org.controllers")
@EntityScan("org.entities")
@EnableJpaRepositories("org.repositories")
@ComponentScan("org.service")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class GestionComptesServerApplication implements CommandLineRunner {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private EmailController emailController;
	@Autowired
	private CarnetRepository groupeRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private EmailService mailService;

	@Autowired
	private GroupeService groupeService;

	public static void main(String[] args) {
		SpringApplication.run(GestionComptesServerApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		// emailController.SaveMail();
/*		compteRepository.save(new Compte("User", "ahmedbenkiran@outlook.fr", "smtp.office365.com", 587,
				"outlook.office365.com", "aA78951238"));
		compteRepository.save(
				new Compte("Ahmed", "ahmedbenkiran0@gmail.com", "smtp.gmail.com", 587, "imap.gmail.com", "aA78951238"));

		Carnet g = new Carnet("AAAAAAAAAAAA", new Date());
		groupeRepository.save(g);

		Carnet g1 = new Carnet("BBBBBBBBB", new Date());
		groupeRepository.save(g1);
		Carnet g2 = new Carnet("CCCCCCCC", new Date());
		groupeRepository.save(g2);

		groupeService.saveCarnet(new Contact((long) 1, "ahmedbenkiran0@emsi-edu.ma"));

		groupeService.saveCarnet(new Contact((long) 2, "ahmedbenkiran0@edu.ma"));
		groupeService.saveCarnet(new Contact((long) 2, "zeinebsept95@emsi-edu.ma"));
	/*	*//*
		 * groupeService.saveCarnet(new Contact((long) 3,
		 * "ahmedbenkiran0@emsi-edu.ma"));
		 * 
		 * groupeService.saveCarnet(new Contact((long) 1, "ahmedbenkiran0@edu.ma"));
		 * 
		 * groupeService.saveCarnet(new Contact((long) 2, "zeinebsept95@emsi-edu.ma"));
		 */
		/*accountService.saveRole(new AppRole("USER"));
		//	accountService.saveRole(new AppRole("ADMIN"));
			//mailService.receivemail();

			Stream.of("user1", "user2", "user3").forEach(un -> {
				try {
					accountService.saveUser(un, "1234");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
*/
		//mailService.receivemail();

	}

}
