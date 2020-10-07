package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Agent;
import lambda.work.javaorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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

    @Override
    public Agent getAgentById(long id) {
        Agent result = agentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent with ID '" + id + "' does not exist."));
        return result;
    }

    @Override
    public void clear() {

    }

    @Override
    public void deleteIfUnassigned(long id) {

    }
}
