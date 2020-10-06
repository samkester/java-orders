package lambda.work.javaorders.controllers;

import lambda.work.javaorders.models.Order;
import lambda.work.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServices orderService;

    //http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> getOrderById(@PathVariable long id)
    {
        Order result = orderService.getOrderById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> getOrdersWithAdvance()
    {
        List<Order> result = orderService.getOrdersWithAdvance();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
