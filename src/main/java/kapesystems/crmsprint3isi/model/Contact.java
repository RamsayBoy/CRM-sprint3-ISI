package kapesystems.crmsprint3isi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fullname;
    private LocalDate date;
    private ArrayList<Campaign> campaigns;
    private String description;

    public Contact(String name) {
        this.fullname = name;
        this.date = LocalDate.now();
        this.campaigns = new ArrayList<>();
        this.description = "";
    }

    public Contact(String name, String description) {
        this.fullname = name;
        this.date = LocalDate.now();
        this.campaigns = new ArrayList<>();
        this.description = description;
    }
}