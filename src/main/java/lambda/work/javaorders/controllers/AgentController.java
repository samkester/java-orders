package lambda.work.javaorders.controllers;

import lambda.work.javaorders.models.Agent;
import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    AgentServices agentService;

    //http://localhost:2019/agents/agent/{id}
    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> getAgentById(@PathVariable long id)
    {
        Agent result = agentService.getAgentById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // http://localhost:2019/agents/unassigned/{id}
    @DeleteMapping(value = "/agents/unassigned/{id}")
    public ResponseEntity<?> deleteAgentIfUnassigned(@PathVariable long id)
    {
        agentService.deleteIfUnassigned(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
