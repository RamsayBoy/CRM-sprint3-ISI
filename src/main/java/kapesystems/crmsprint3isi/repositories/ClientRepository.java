package kapesystems.crmsprint3isi.repositories;

import kapesystems.crmsprint3isi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Client findById(Long id);
}
