package kapesystems.crmsprint3isi.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private Date signUpDate;

    @ManyToOne
    private Client client;


    public Campaign() {}

    public Campaign(String title, Client client) {
        this.title = title;
        this.signUpDate = java.sql.Date.valueOf(LocalDate.now());
        this.client = client;
    }

    public Campaign(String title, Client client, String date) throws ParseException {
        this.title = title;
        this.signUpDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        this.client = client;
    }
    public Campaign(String title) {
        this.title = title;
        this.signUpDate = java.sql.Date.valueOf(LocalDate.now());
    }

    public Campaign(String title,String date) throws ParseException {
        this.title = title;
        this.signUpDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        // TODO: Make it work without comments
        //int numCampaigns = this.getClient().getNumCampaigns();
        this.client = client;
        //this.getClient().setNumCampaigns(numCampaigns);
    }
}