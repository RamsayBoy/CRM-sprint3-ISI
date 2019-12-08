package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepo;

    @RequestMapping()
    public String campaigns(Model model) {
        List campaigns = campaignRepo.findAll();

        if(!campaigns.isEmpty()){
            model.addAttribute("areCampaigns", true);

            model.addAttribute("campaigns", campaigns);
        }
        return "campaigns";
    }

    @RequestMapping("/{id}")
    public String campaign(Model model, @PathVariable Long id) {
        model.addAttribute("campaign", campaignRepo.findById(id));
        return "campaign";
    }

    @RequestMapping("/post/{id}")
    public String editCampaign(Model model, @PathVariable Long id) {
        return "redirect:/campaigns";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCampaign(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Campaign campaign = campaignRepo.findById(id);

        if(campaign.getClient() != null) {
            campaign.setClient(null);
            campaignRepo.delete(campaign);
            redirectAttributes.addFlashAttribute("redirectMsg", "La campaña " + campaign.getTitle()
                    + " ha sido eliminado con éxito.");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "La campaña "+ campaign.getTitle()+" no ha podido eliminarse.");
        }

        return "redirect:/campaigns";
    }
}
