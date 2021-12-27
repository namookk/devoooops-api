package info.devoooops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//test
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class DevOooopsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevOooopsApiApplication.class, args);
    }

}
