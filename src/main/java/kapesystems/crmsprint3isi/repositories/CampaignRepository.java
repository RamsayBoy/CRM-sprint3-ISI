package kapesystems.crmsprint3isi.repositories;

import kapesystems.crmsprint3isi.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
