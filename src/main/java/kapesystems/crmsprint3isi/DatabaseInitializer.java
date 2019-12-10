package kapesystems.crmsprint3isi;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.repositories.CampaignRepository;
import kapesystems.crmsprint3isi.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class DatabaseInitializer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CampaignRepository campaignRespository;

    @Override
    public void run(String... args) throws Exception {

        Client client1 = new Client("Daslukia", "28/03/2019");
        Client client2 = new Client("PapuCar", "17/07/2019");
        Client client3 = new Client("KAPEWARE", "02/05/2019");
        Client client4 = new Client("CoreDump", "25/02/2018");

        Campaign campaing1 = new Campaign(
                "Summer Time",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc feugiat consectetur sodales." +
                        " Etiam eu vehicula leo.",
                "15/06/2019", "15/09/2019");
        Campaign campaign2 = new Campaign(
                "Navidad por doquier",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc feugiat consectetur sodales." +
                        " Etiam eu vehicula leo. Maecenas quis porta ex.",
                "02/05/2019", "29/05/2019");
        Campaign campaign3 = new Campaign(
                "Black Friday",
                "alkndsl askdklad asdlkasd",
                "25/02/2018", "17/03/2018");

        // It is necessary to set up campaigns before save them in the repository because are the main entity
        campaing1.setClient(client1);
        campaign2.setClient(client2);
        campaign3.setClient(client2);

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
        clientRepository.save(client4);

        campaignRespository.save(campaing1);
        campaignRespository.save(campaign2);
        campaignRespository.save(campaign3);

        logger.info("Database initialized successfully");
    }
}