package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.repositories.CampaignRepository;
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

    @RequestMapping()
    public String campaigns(Model model) {
        List campaigns = campaignRepo.findAll();

        if(!campaigns.isEmpty())
            model.addAttribute("areCampaigns", true);

        model.addAttribute("campaigns", campaigns);

        return "campaigns";
    }

    @RequestMapping("/{id}")
    public String campaign(Model model, @PathVariable Long id) {
        model.addAttribute("campaign", campaignRepo.findById(id));
        return "campaign";
    }

    @RequestMapping("/create")
    public String createCampaign() {
        return "createCampaign";
    }

    @RequestMapping("/add")
    public String addCampaign(@ModelAttribute("campaign") Campaign newCampaign, RedirectAttributes redirectAttributes) {
        if(newCampaign != null) {
            campaignRepo.save(newCampaign);
            redirectAttributes.addFlashAttribute("redirectMsg", "La campaña " + newCampaign.getTitle()
                    + " ha sido añadido con éxito.");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "Hubo un error al intentar añadir la campaña");
        }

        return "redirect:/campaigns";
    }

    @RequestMapping("{id}/edit")
    public String editCampaign(RedirectAttributes redirectAttributes, @PathVariable long id) {
        redirectAttributes.addFlashAttribute("editMode", true);
        return "redirect:/campaigns/" + id;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public String postClient(Model model, @PathVariable Long id, @ModelAttribute("campaign")Campaign campaignEdited,
                             RedirectAttributes redirectAttributes) {
        Optional<Campaign> campaigns = campaignRepo.findById(id);

        if(campaigns.isPresent()) {
            campaignRepo.save(campaignEdited);
            redirectAttributes.addFlashAttribute("editClientMsg", "El cliente ha sido modificado");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "El cliente no se ha podido editar");
        }

        return "redirect:/clients";
    }

    /*@RequestMapping("/delete/{id}")
    public String deleteCampaign(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Campaign> campaigns = campaignRepo.findById(id);

        if(campaigns.isPresent()) {
            campaign.setClient(null);
            campaignRepo.delete(campaign);
            redirectAttributes.addFlashAttribute("redirectMsg", "La campaña " + campaign.getTitle()
                    + " ha sido eliminado con éxito.");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "La campaña "+ campaign.getTitle()+" no ha podido eliminarse.");
        }

        return "redirect:/campaigns";
    }*/
}
