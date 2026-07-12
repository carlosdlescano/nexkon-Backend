package com.minegocio.minegocio2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
// El ComponentScan es clave: le dice a Spring que busque componentes en la raíz del proyecto
@ComponentScan(basePackages = "com.minegocio") 

public class App {

    public static void main(String[] args) {
        // Esta línea apaga el entorno gráfico de escritorio y levanta el servidor web Tomcat
        SpringApplication.run(App.class, args);
        System.out.println("🚀 ¡Servidor de NexKon corriendo localmente en http://localhost:8080!");
    }
}