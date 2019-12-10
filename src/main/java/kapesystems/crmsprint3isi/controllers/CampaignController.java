package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
