package kapesystems.crmsprint3isi.controllers;

import kapesystems.crmsprint3isi.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ContactsController {

    @Autowired
    private ContactRepository clientRepo;

    @RequestMapping("/contacts")
    public String contacts(Model model) {
        List contacts = clientRepo.findAll();

        if(!contacts.isEmpty())
            model.addAttribute("areContacts", true);

        model.addAttribute("contacts", contacts);

        return "contacts";
    }
}
