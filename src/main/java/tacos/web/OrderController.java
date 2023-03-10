package tacos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import tacos.data.OrderRepository;
import tacos.domain.TacoOrder;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public record OrderController(OrderRepository orderRepository, OrderProps props) {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/current")
    public String orderForm(
            @ModelAttribute TacoOrder tacoOrder) {

        tacoOrder.setCcNumber("38520000023237");

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order,
    		Errors errors,
            SessionStatus sessionStatus) {
    	
    	if (errors.hasErrors()) {
    		return "orderForm";
    	}

        orderRepository.save(order);

        LOG.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
