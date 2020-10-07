package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Agent;

public interface AgentServices {
    Agent save(Agent agent);

    Agent getAgentById(long id);

    void clear();

    void deleteIfUnassigned(long id);
}
