package kapesystems.crmsprint3isi;

import kapesystems.crmsprint3isi.model.Contact;
import kapesystems.crmsprint3isi.repositories.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseInitializer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void run(String... args) throws Exception {

        contactRepository.save(new Contact("Manolete", "El del bombo"));
        contactRepository.save(new Contact("Pepe", "El del carrillo"));
        contactRepository.save(new Contact("Barajas", "Tira pa' la fregoneta"));
        contactRepository.save(new Contact("Juan", "Guerrero despiadado"));

        logger.info("Database initialized successfully");
    }
}