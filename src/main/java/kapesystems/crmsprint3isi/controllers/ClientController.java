package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepo;

    @RequestMapping("/clients")
    public String clients(Model model) {
        List clients = clientRepo.findAll();

        if(!clients.isEmpty())
            model.addAttribute("areClients", true);

        model.addAttribute("clients", clients);

        return "clients";
    }

    @RequestMapping("/clients/post/{id}")
    public String editClient(Model model, @PathVariable Long id) {
        return "redirect:/clients";
    }

    // https://www.youtube.com/watch?v=bdVKdMZNjOY
    @RequestMapping("/clients/delete/{id}")
    public String deleteClient(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Client client = clientRepo.findById(id);

        if(client != null) {
            for(Campaign campaign : client.getCampaigns()) {
                campaign.setClient(null);
            }

            clientRepo.delete(client);
            redirectAttributes.addFlashAttribute("redirectMsg", "El cliente " + client.getName()
                    + " ha sido eliminado con éxito.");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "El cliente no ha podido eliminarse.");
        }

        return "redirect:/clients";
    }
}
