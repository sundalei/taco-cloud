package tacos.api;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tacos.data.OrderRepository;
import tacos.domain.TacoOrder;

//@RestController
//@RequestMapping("/api/orders")
public class TacoOrderController {

    private final OrderRepository orderRepository;

    public TacoOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/deliveryZip/{deliveryZip}")
    public List<TacoOrder> findOrdersByDeliveryZip(@PathVariable String deliveryZip) {
        return orderRepository.findByDeliveryZip(deliveryZip);
    }

    @GetMapping("/deliveryZip/{deliveryZip}/startDate/{startDate}/endDate/{endDate}")
    public List<TacoOrder> findOrdersByDeliveryZipAndDate(@PathVariable String deliveryZip,
            @PathVariable Date startDate, @PathVariable Date endDate) {
        return orderRepository.readOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip, startDate, endDate);
    }

}
