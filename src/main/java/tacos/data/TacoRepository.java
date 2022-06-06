package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.Taco;

import java.util.UUID;

public interface TacoRepository extends CrudRepository<Taco, UUID> {
}
