package s.example.sara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "s")
@EntityScan(basePackages = "s.model")
@EnableJpaRepositories(basePackages = "s.repository")
public class SaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaraApplication.class, args);
	}

}
