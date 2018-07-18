package br.com.befullstack.eiticrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DockerRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DockerRunner.class);

    @Override
    public void run(String... strings) throws Exception {

        LOGGER.info("Running MySQL in Docker!");

        String command = "ls -al";

        Runtime runtime = Runtime.getRuntime();

        Process process = runtime.exec(command);

        process.waitFor();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            LOGGER.info(line);
        }

    }
}
