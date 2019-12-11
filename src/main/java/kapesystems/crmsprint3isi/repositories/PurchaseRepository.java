package kapesystems.crmsprint3isi.repositories;

import kapesystems.crmsprint3isi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
