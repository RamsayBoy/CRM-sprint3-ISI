package kapesystems.crmsprint3isi.model;

import javax.persistence.*;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @ManyToOne
    private Client client;

    public Campaign() {}

    public Campaign(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
