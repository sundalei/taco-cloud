package tacos.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderRepository;
import tacos.domain.TacoOrder;
import tacos.domain.TacoUser;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProps props;

    public OrderController(OrderRepository orderRepository,
            OrderProps props) {
        this.orderRepository = orderRepository;
        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal TacoUser user,
            @ModelAttribute TacoOrder tacoOrder) {
        if (tacoOrder.getDeliveryName() == null) {
            tacoOrder.setDeliveryName(user.getFullname());
        }
        if (tacoOrder.getDeliveryStreet() == null) {
            tacoOrder.setDeliveryStreet(user.getStreet());
        }
        if (tacoOrder.getDeliveryCity() == null) {
            tacoOrder.setDeliveryCity(user.getCity());
        }
        if (tacoOrder.getDeliveryState() == null) {
            tacoOrder.setDeliveryState(user.getState());
        }
        if (tacoOrder.getDeliveryZip() == null) {
            tacoOrder.setDeliveryZip(user.getZip());
        }

        tacoOrder.setCcNumber("38520000023237");

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal TacoUser user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("process order, order user {}", user);
        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();

        log.info("Order submitted: {}", order);

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal TacoUser user, Model model) {

        Pageable pageable = PageRequest.of(0, props.getPageSize());

        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
