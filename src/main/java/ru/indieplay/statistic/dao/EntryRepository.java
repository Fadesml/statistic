package ru.indieplay.statistic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import ru.indieplay.statistic.models.AgentSender;
import ru.indieplay.statistic.models.Entry;
import ru.indieplay.statistic.models.Event;

/**
 * Created by Fadesml on 23.05.2021.
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
    void deleteAllByEvent(Event event);
    void deleteAllByAgentSender(AgentSender agentSender);
}
