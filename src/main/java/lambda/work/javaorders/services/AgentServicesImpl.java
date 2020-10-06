package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Agent;
import lambda.work.javaorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "agentService")
public class AgentServicesImpl implements AgentServices {
    @Autowired
    AgentRepository agentRepository;

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }
}
