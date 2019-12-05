package kapesystems.crmsprint3isi;

import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseInitializer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {

        clientRepository.save(new Client("Daslukia", "28/03/2019"));
        clientRepository.save(new Client("Papuc", "17/07/2019"));
        clientRepository.save(new Client("Koalas de la Información", "02/05/2019"));
        clientRepository.save(new Client("CoreDump", "25/02/2018"));

        logger.info("Database initialized successfully");
    }
}