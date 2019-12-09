package kapesystems.crmsprint3isi.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date signUpDate;
    @OneToMany(mappedBy = "client")
    private List<Campaign> campaigns;
    // TODO: See how to update this value when a new campaign is added (see campaign.java setClient method)
    private int numCampaigns = 0;

    public Client() {}

    // TODO: Change this.attribute to setAttribute()
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

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public int getNumCampaigns() {
        return numCampaigns;
    }

    public void setNumCampaigns(int numCampaigns) {
        this.numCampaigns = numCampaigns;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", signUpDate=" + signUpDate +
                ", campaigns=" + campaigns +
                ", numCampaigns=" + numCampaigns +
                '}';
    }
}
