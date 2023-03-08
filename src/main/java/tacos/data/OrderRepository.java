package tacos.data;

import java.util.Date;
import java.util.List;

import tacos.domain.TacoOrder;

public interface OrderRepository {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
