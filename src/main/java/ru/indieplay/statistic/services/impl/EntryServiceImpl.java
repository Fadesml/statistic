package ru.indieplay.statistic.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.indieplay.statistic.dao.AgentSenderRepository;
import ru.indieplay.statistic.dao.EntryRepository;
import ru.indieplay.statistic.dao.EventRepository;
import ru.indieplay.statistic.models.AgentSender;
import ru.indieplay.statistic.models.Entry;
import ru.indieplay.statistic.models.Event;
import ru.indieplay.statistic.payload.request.EntryRequest;
import ru.indieplay.statistic.payload.response.ServerResponse;
import ru.indieplay.statistic.services.IEntryService;

/**
 * Created by Fadesml on 23.05.2021.
 */

@Service
public class EntryServiceImpl implements IEntryService {
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private AgentSenderRepository agentSenderRepository;

    @Autowired
    private EventRepository eventRepository;

    @SneakyThrows
    @Override
    public ResponseEntity<?> create(EntryRequest request) {
        if (request.emptyValues().isEmpty()) {
            if (!agentSenderRepository.existsById(request.getAgentSenderId())) {
                return ServerResponse.response(false, "Agent Sender with id: " + request.getAgentSenderId() + " does not exist!", HttpStatus.BAD_REQUEST);
            }

            if (!eventRepository.existsById(request.getEventId())) {
                return ServerResponse.response(false, "Event with id: " + request.getAgentSenderId() + " does not exist!", HttpStatus.BAD_REQUEST);
            }

            AgentSender agentSender = agentSenderRepository.findById(request.getAgentSenderId()).get();
            Event event = eventRepository.findById(request.getEventId()).get();
            Entry entry = new Entry(agentSender, request.getAgentData(), new ObjectMapper().writeValueAsString(request.getData()), event);
            entryRepository.save(entry);

            return ServerResponse.response(true, "Entry successful created", HttpStatus.OK);
        } else {
            return ServerResponse.response(false, "Some values are empty: " + request.emptyValues().toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        if (entryRepository.existsById(id)) {
            entryRepository.deleteById(id);
            return ServerResponse.response(true, "Entry successful deleted", HttpStatus.OK);
        }
        return ServerResponse.response(false, "Entry with id: "+ id + " does not exist!", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> deleteAll() {
        entryRepository.deleteAll();
        return ServerResponse.response(true, "All Entries successful deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAllByEvent(Long id) {
        if (eventRepository.existsById(id)) {
            entryRepository.deleteAllByEvent(eventRepository.findById(id).get());
            return ServerResponse.response(true, "Entries successful deleted", HttpStatus.OK);
        }
        return ServerResponse.response(false, "Event with id: "+ id + " does not exist!", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> deleteAllByAgentSender(Long id) {
        if (eventRepository.existsById(id)) {
            entryRepository.deleteAllByAgentSender(agentSenderRepository.findById(id).get());
            return ServerResponse.response(true, "Entries successful deleted", HttpStatus.OK);
        }
        return ServerResponse.response(false, "Agent Sender with id: "+ id + " does not exist!", HttpStatus.NOT_FOUND);
    }
}
