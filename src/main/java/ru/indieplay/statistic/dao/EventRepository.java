package ru.indieplay.statistic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.indieplay.statistic.models.Event;

/**
 * Created by Fadesml on 23.05.2021.
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
