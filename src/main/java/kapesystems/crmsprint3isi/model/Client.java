package kapesystems.crmsprint3isi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date signUpDate;
    private ArrayList<Campaign> campaigns;

    public Client() {}

    public Client(String name) {
        this.name = name;
        this.signUpDate = java.sql.Date.valueOf(LocalDate.now()); // LocalDate.now() to Data type
        this.campaigns = new ArrayList<>();
    }

    public Client(String name, String date) throws ParseException {
        this.name = name;
        this.signUpDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        this.campaigns = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(ArrayList<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", signUpDate=" + signUpDate +
                ", campaigns=" + campaigns +
                '}';
    }
}
