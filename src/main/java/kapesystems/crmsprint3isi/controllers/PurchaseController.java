package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("purchases")
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepo;

    @RequestMapping()
    public String purchases(Model model) {
        List purchases = purchaseRepo.findAll();

        if(!purchases.isEmpty()) {
            model.addAttribute("arePurchases", true);
        }

        model.addAttribute("purchases", purchases);

        return "purchases";
    }
}
