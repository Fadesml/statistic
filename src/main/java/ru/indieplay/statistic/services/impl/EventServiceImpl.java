package ru.indieplay.statistic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.indieplay.statistic.dao.EventRepository;
import ru.indieplay.statistic.models.Event;
import ru.indieplay.statistic.payload.request.INDRequest;
import ru.indieplay.statistic.payload.response.AbstractResponse;
import ru.indieplay.statistic.payload.response.ServerResponse;
import ru.indieplay.statistic.services.IEventService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fadesml on 23.05.2021.
 */
@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    private EventRepository repository;
    
    @Override
    public ResponseEntity<?> getAll() {
        if (repository.count() != 0) {
            List<AbstractResponse> list = new ArrayList<>();
            repository.findAll().forEach(object -> {
                list.add(object.toResponse());
            });
            return ServerResponse.response(true, list, HttpStatus.OK);
        }

        return ServerResponse.response(false, "Events not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        if (repository.existsById(id)) {
            return ServerResponse.response(true, repository.findById(id).get().toResponse(), HttpStatus.OK);
        }

        return ServerResponse.response(false, "Event not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> create(INDRequest request) {
        if (request.hasId()) {
            if (repository.existsById(request.getId())) {
                return ServerResponse.response(false, "Event with id: " + request.getId() + " already exists!", HttpStatus.BAD_REQUEST);
            }
        }

        if (request.emptyValues().isEmpty()) {
            repository.save(new Event(request.getId(), request.getName(), request.getDescription()));
            return ServerResponse.response(true, "Event successful created", HttpStatus.OK);
        } else {
            return ServerResponse.response(false, "Some values are empty: " + request.emptyValues().toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> edit(Long id, INDRequest request) {
        if (repository.existsById(id)) {
            return ServerResponse.response(false, "Event with id: " + request.getId() + " does not exist!", HttpStatus.NOT_FOUND);
        }
        if (request.hasId()) {
            if (repository.existsById(request.getId())) {
                return ServerResponse.response(false, "Event with id: " + request.getId() + " already exists!", HttpStatus.BAD_REQUEST);
            }
        }

        Event event = repository.findById(id).get();
        event.edit(request);
        repository.save(event);

        return ServerResponse.response(true, "Event edited successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ServerResponse.response(true, "Event deleted successfully", HttpStatus.OK);
        } else {
            return ServerResponse.response(true, "Event with id: " + id + " does not exist!", HttpStatus.BAD_REQUEST);
        }
    }
}
