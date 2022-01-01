package pro.ruloff.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableScheduling
@Configuration
public class CoreConfiguration implements WebMvcConfigurer {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
