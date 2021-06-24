package ru.indieplay.statistic.services;

import org.springframework.http.ResponseEntity;
import ru.indieplay.statistic.payload.request.EntryRequest;

/**
 * Created by Fadesml on 23.05.2021.
 */
public interface IEntryService {
    ResponseEntity<?> create(EntryRequest request);
    ResponseEntity<?> deleteById(Long id);
    ResponseEntity<?> deleteAll();
    ResponseEntity<?> deleteAllByEvent(Long id);
    ResponseEntity<?> deleteAllByAgentSender(Long id);
}
