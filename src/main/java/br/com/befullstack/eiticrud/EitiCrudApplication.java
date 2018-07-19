package br.com.befullstack.eiticrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EitiCrudApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EitiCrudApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(EitiCrudApplication.class, args);

        LOGGER.info("\n" +
                "___________.__  __  .__    _________      .__          __  .__                      \n" +
                "\\_   _____/|__|/  |_|__|  /   _____/ ____ |  |  __ ___/  |_|__| ____   ____   ______\n" +
                " |    __)_ |  \\   __\\  |  \\_____  \\ /  _ \\|  | |  |  \\   __\\  |/  _ \\ /    \\ /  ___/\n" +
                " |        \\|  ||  | |  |  /        (  <_> )  |_|  |  /|  | |  (  <_> )   |  \\\\___ \\ \n" +
                "/_______  /|__||__| |__| /_______  /\\____/|____/____/ |__| |__|\\____/|___|  /____  >\n" +
                "        \\/                       \\/                                       \\/     \\/ \n" +
                "");
    }
}


