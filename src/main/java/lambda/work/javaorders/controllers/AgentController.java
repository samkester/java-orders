package lambda.work.javaorders.controllers;

import lambda.work.javaorders.models.Agent;
import lambda.work.javaorders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
