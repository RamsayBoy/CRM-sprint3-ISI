package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.model.Campaign;
import kapesystems.crmsprint3isi.model.Client;
import kapesystems.crmsprint3isi.model.Purchase;
import kapesystems.crmsprint3isi.repositories.ClientRepository;
import kapesystems.crmsprint3isi.repositories.PurchaseRepository;
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
@RequestMapping("purchases")
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepo;

    @Autowired
    ClientRepository clientRepo;

    @RequestMapping()
    public String purchases(Model model) {
        List purchases = purchaseRepo.findAll();

        if(!purchases.isEmpty()) {
            model.addAttribute("arePurchases", true);
        }

        model.addAttribute("purchases", purchases);

        double[] earnedMoney = new double[12];

        for(Object object : purchases) {
            Purchase purchase = (Purchase) object;

            switch (purchase.getMonth()) {
                case "ENERO":
                    earnedMoney[0] += purchase.getPrice();
                    break;
                case "FEBRERO":
                    earnedMoney[1] += purchase.getPrice();
                    break;
                case "MARZO":
                    earnedMoney[2] += purchase.getPrice();
                    break;
                case "ABRIL":
                    earnedMoney[3] += purchase.getPrice();
                    break;
                case "MAYO":
                    earnedMoney[4] += purchase.getPrice();
                    break;
                case "JUNIO":
                    earnedMoney[5] += purchase.getPrice();
                    break;
                case "JULIO":
                    earnedMoney[6] += purchase.getPrice();
                    break;
                case "AGOSTO":
                    earnedMoney[7] += purchase.getPrice();
                    break;
                case "SEPTIEMBRE":
                    earnedMoney[8] += purchase.getPrice();
                    break;
                case "OCTUBRE":
                    earnedMoney[9] += purchase.getPrice();
                    break;
                case "NOVIEMBRE":
                    earnedMoney[10] += purchase.getPrice();
                    break;
                case "DICIEMBRE":
                    earnedMoney[11] += purchase.getPrice();
                    break;
            } // switch
        } // for

        for(int i = 0; i < 12; i++)
            model.addAttribute("earnedMoney" + i, earnedMoney[i]);

        return "purchases";
    }

    @RequestMapping("/{id}")
    public String purchase(Model model, @PathVariable long id) {
        Optional<Purchase> purchase = purchaseRepo.findById(id);

        if(purchase.isPresent()) {
            model.addAttribute("purchase", purchase.get());
        }

        return "purchase";
    }

    @RequestMapping("/create")
    public String createPurchase(RedirectAttributes redirectAttributes) {
        return "redirect:/purchases/create";
    }

    @RequestMapping("/add")
    public String addPurchase(@ModelAttribute("purchase")Purchase newPurchase, RedirectAttributes redirectAttributes) {
        if(newPurchase != null) {
            purchaseRepo.save(newPurchase);
            redirectAttributes.addFlashAttribute("redirectMsg", "La compra " + newPurchase.getProductName()
                    + " ha sido añadida con éxito.");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "Hubo un error al intentar añadir la compra");
        }

        return "redirect:/purchases";
    }

    @RequestMapping("{id}/edit")
    public String editPurchase(RedirectAttributes redirectAttributes, @PathVariable long id) {
        redirectAttributes.addFlashAttribute("editMode", true);

        Optional purchase = purchaseRepo.findById(id);

        if(purchase.isPresent()) {
            Purchase purchaseToEdit = (Purchase) purchase.get();
            redirectAttributes.addFlashAttribute("purchase", purchaseToEdit);
        }

        return "redirect:/purchases/" + id;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public String postPurchase(Model model, @PathVariable Long id, @ModelAttribute("purchase") Purchase purchaseEdited,
                             RedirectAttributes redirectAttributes) {
        Optional<Purchase> purchase = purchaseRepo.findById(id);
        Client client = clientRepo.findByName(purchaseEdited.getClientName());

        if(client == null)
            purchaseEdited.setClientName("DESCONOCIDO");

        if(purchase.isPresent()) {
            purchaseEdited.setClient(client);
            purchaseRepo.save(purchaseEdited);
            redirectAttributes.addFlashAttribute("editClientMsg", "La compra ha sido modificada");
        }
        else {
            redirectAttributes.addFlashAttribute("redirectErrMsg", "La compra no se ha podido editar");
        }

        return "redirect:/purchases";
    }
}
