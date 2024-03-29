package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.repositories.CampaignRepository;
import kapesystems.crmsprint3isi.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepo;

    @Autowired
    private ClientRepository clientRepo;

    @RequestMapping()
    public String campaigns(Model model) {
        List campaigns = campaignRepo.findAll();

        if(!campaigns.isEmpty())
            model.addAttribute("areCampaigns", true);

        model.addAttribute("campaigns", campaigns);

        return "campaigns";
    }

    @RequestMapping("/{id}")
    public String campaign(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Optional campaign = campaignRepo.findById(id);

        if(campaign.isPresent()) {
            Campaign campaignToEdit = (Campaign) campaign.get();
            redirectAttributes.addFlashAttribute("campaign", campaignToEdit);
        }

        return "redirect:/campaigns";
    }

    @RequestMapping("/create")
    public String createCampaign(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("createMode", true);

        return "redirect:/campaigns";
    }

    @RequestMapping("/add")
    public String addCampaign(@ModelAttribute("campaign") Campaign campaign, RedirectAttributes redirectAttributes) {
        Client client = clientRepo.findByName(campaign.getClientName());

        if(campaign != null) {
            campaign.setClient(client);
            campaignRepo.save(campaign);
            redirectAttributes.addFlashAttribute("redirectMsg", "La campaña " + campaign.getTitle()
                    + " ha sido añadida con éxito.");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "Hubo un error al intentar añadir el cliente");
        }

        return "redirect:/campaigns";
    }

    @RequestMapping("{id}/edit")
    public String editCampaign(RedirectAttributes redirectAttributes, @PathVariable long id) {
        redirectAttributes.addFlashAttribute("editMode", true);

        Optional campaign = campaignRepo.findById(id);

        if(campaign.isPresent()) {
            Campaign campaignToEdit = (Campaign) campaign.get();
            campaignToEdit.getDescription().trim();
            redirectAttributes.addFlashAttribute("campaign", campaignToEdit);
        }

        return "redirect:/campaigns";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public String postCampaign(@PathVariable Long id, @ModelAttribute("campaign") Campaign campaignEdited,
                               RedirectAttributes redirectAttributes) {
        Optional<Campaign> campaign = campaignRepo.findById(id);
        Client client = clientRepo.findByName(campaignEdited.getClientName());

        if(campaign.isPresent()) {
            campaignEdited.setClient(client);
            campaignRepo.save(campaignEdited);
            redirectAttributes.addFlashAttribute("editClientMsg", "La campaña ha sido modificada");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "La campaña no se ha podido editar");
        }

        return "redirect:/campaigns";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCampaign(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Optional<Campaign> campaign = campaignRepo.findById(id);

        if(campaign.isPresent()) {
            Campaign campaignToDelete = campaign.get();
            campaignRepo.delete(campaignToDelete);

            redirectAttributes.addFlashAttribute("redirectMsg", "La campaña " + campaignToDelete.getTitle()
                    + " ha sido eliminada con éxito.");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "La campaña no ha podido eliminarse.");
        }

        return "redirect:/campaigns";
    }
}
