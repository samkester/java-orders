package lambda.work.javaorders.controllers;

import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.services.CustomerServices;
import lambda.work.javaorders.views.CustomerOrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerServices customerService;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> getCustomers()
    {
        List<Customer> result = customerService.getCustomers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerById(@PathVariable long id)
    {
        Customer result = customerService.getCustomerById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/{substring}
    @GetMapping(value = "/namelike/{substring}", produces = {"application/json"})
    public ResponseEntity<?> getCustomersByNameSubstring(@PathVariable String substring)
    {
        List<Customer> result = customerService.getCustomersByNameSubstring(substring);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:2019/customers/orders/count
    @GetMapping(value = "/orders/count", produces = {"application/json"})
    public ResponseEntity<?> getCustomersByOrderCount()
    {
        List<CustomerOrderCount> result = customerService.getCustomersByOrderCount();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> postCustomer(@Valid @RequestBody Customer body)
    {
        body = customerService.save(body);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // http://localhost:2019/customers/customer/{id}
    @PutMapping(value = "/customer/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> putCustomer(@Valid @RequestBody Customer body, @PathVariable long id)
    {
        body.setCustcode(id);
        body = customerService.save(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @PatchMapping(value = "/customer/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> patchCustomer(@Valid @RequestBody Customer body, @PathVariable long id)
    {
        body = customerService.update(body, id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id)
    {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
