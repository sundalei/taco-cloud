package tacos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import tacos.data.OrderRepository;
import tacos.domain.TacoOrder;

//@Controller
//@RequestMapping("/orders")
//@SessionAttributes("tacoOrder")
public record OrderController(OrderRepository orderRepository, OrderProps props) {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/current")
    public String orderForm(
            @ModelAttribute TacoOrder tacoOrder) {

        tacoOrder.setCcNumber("38520000023237");

        return "orderForm";
    }

    @PostMapping
    public String processOrder(TacoOrder order,
            SessionStatus sessionStatus) {

        //orderRepository.save(order);

        LOG.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
