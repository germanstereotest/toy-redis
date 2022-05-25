package open.gpesce.toyredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ToyRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyRedisApplication.class, args);
	}

}
