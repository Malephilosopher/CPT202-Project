package com.xjtlu.cpt202.cpt202Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
//@EnableCaching
public class CPT202ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CPT202ProjectApplication.class, args);
    }

}
