package com.microservicioszuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



//PARA QUE SEA CLIENTE SE ZUUL, SERIVODOR PARA ENRUTAR LOS MICROSERVICIOS
@EnableZuulProxy
//PARA QUE SEA CLIENTE DE EUREKA
@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosZuulApplication.class, args);
	}

}
