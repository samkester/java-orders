package lambda.work.javaorders.controllers;

import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.models.Order;
import lambda.work.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> postOrder(@Valid @RequestBody Order body)
    {
        body = orderService.save(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // http://localhost:2019/orders/order/{id}
    @PutMapping(value = "/order/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> putOrder(@Valid @RequestBody Order body, @PathVariable long id)
    {
        body.setOrdnum(id);
        body = orderService.save(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // http://localhost:2019/orders/order/{id}
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable long id)
    {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
