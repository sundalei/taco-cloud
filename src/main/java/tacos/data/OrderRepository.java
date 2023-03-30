package tacos.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.domain.TacoOrder;
import tacos.domain.User;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
