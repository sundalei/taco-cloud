package tacos.api;

import java.util.Date;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderRepository;
import tacos.domain.TacoOrder;
import tacos.domain.TacoUser;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@ConfigurationProperties(prefix = "taco.orders")
public class TacoOrderController {

    private int pageSize = 20;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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

    @GetMapping
    public List<TacoOrder> findOrdersByUser(
            @AuthenticationPrincipal TacoUser user) {
        log.info("page size: {}", pageSize);
        Pageable pageable = PageRequest.of(0, pageSize);
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

}
