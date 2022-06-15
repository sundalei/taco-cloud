package tacos.orderadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class OrderAdminController {
    
    private final OrderAdminService adminService;

    public OrderAdminController(OrderAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String listOrders() {
        return "admin";
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        log.info("delete all orders.");
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
