package kapesystems.crmsprint3isi;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.model.Purchase;
import kapesystems.crmsprint3isi.repositories.CampaignRepository;
import kapesystems.crmsprint3isi.repositories.ClientRepository;
import kapesystems.crmsprint3isi.repositories.PurchaseRepository;
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

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void run(String... args) throws Exception {

        Client client1 = new Client("Daslukia", "28/03/2019");
        Client client2 = new Client("PapuCar", "17/07/2019");
        Client client3 = new Client("Berkhire", "02/05/2019");
        Client client4 = new Client("CoreDump", "25/02/2018");
        Client client5 = new Client("Starbukos", "24/01/2019");
        Client client6 = new Client("IMB", "10/12/2019");
        Client client7 = new Client("Zonama", "25/09/2019");
        Client client8 = new Client("Glegoo", "21/11/2019");

        Campaign campaing1 = new Campaign(
                "Summer Time",
                "Una buena campaña de verano para hacer cosas chulas.",
                "15/06/2019", "15/09/2019");
        Campaign campaign2 = new Campaign(
                "Navidad por doquier",
                "Con la navidad hay nuevos servicios fresquísimos.",
                "12/12/2019", "25/12/2019");
        Campaign campaign3 = new Campaign(
                "Black week",
                "Todas las rebajas posibles en nuestros servicios",
                "20/02/2018", "27/02/2018");

        Purchase purchase1 = new Purchase("Checkeo de finanzas", 200, "ENERO");
        Purchase purchase2 = new Purchase("sd", 2000, "JUNIO");
        Purchase purchase3 = new Purchase("asdasdad", 20, "DICIEMBRE");
        Purchase purchase4 = new Purchase("Servicio de algo", 350.20, "JUNIO");
        Purchase purchase5 = new Purchase("blablah", 30, "AGOSTO");

        // It is necessary to set up campaigns before save them in the repository because are the main entity
        campaing1.setClient(client1);
        campaign2.setClient(client2);
        campaign3.setClient(client2);

        purchase1.setClient(client1);
        purchase2.setClient(client2);
        purchase3.setClient(client2);
        purchase4.setClient(client5);
        purchase5.setClient(client2);

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
        clientRepository.save(client4);
        clientRepository.save(client5);
        clientRepository.save(client6);
        clientRepository.save(client7);
        clientRepository.save(client8);

        campaignRepository.save(campaing1);
        campaignRepository.save(campaign2);
        campaignRepository.save(campaign3);

        purchaseRepository.save(purchase1);
        purchaseRepository.save(purchase2);
        purchaseRepository.save(purchase3);
        purchaseRepository.save(purchase4);
        purchaseRepository.save(purchase5);

        logger.info("Database initialized successfully");
    }
}