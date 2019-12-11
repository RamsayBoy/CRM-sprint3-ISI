package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.model.Purchase;
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
}
