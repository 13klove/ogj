package com.op.gg.ogj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OgjApplication {

    public static void main(String[] args) {
        SpringApplication.run(OgjApplication.class, args);
    }

}
