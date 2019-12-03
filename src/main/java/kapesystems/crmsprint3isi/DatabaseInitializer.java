package kapesystems.crmsprint3isi;

import kapesystems.crmsprint3isi.model.Contact;
import kapesystems.crmsprint3isi.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private ContactRepository clientRepo;

    public DatabaseInitializer(ContactRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        clientRepo.save(new Contact("Manolo", "El del bombo"));
        clientRepo.save(new Contact("Pepe", "El del carrillo"));
        clientRepo.save(new Contact("Barajas", "Tira pa' la fregoneta"));
        clientRepo.save(new Contact("Juan", "Guerrero despiadado"));

        System.out.println("Database initialized successfully");
    }
}
