package ru.indieplay.statistic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.indieplay.statistic.dao.AgentSenderRepository;
import ru.indieplay.statistic.models.AgentSender;
import ru.indieplay.statistic.payload.request.INDRequest;
import ru.indieplay.statistic.payload.response.AbstractResponse;
import ru.indieplay.statistic.payload.response.ServerResponse;
import ru.indieplay.statistic.services.IAgentSenderService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fadesml on 23.05.2021.
 */
@Service
class AgentSenderServiceImpl implements IAgentSenderService {
    @Autowired
    private AgentSenderRepository repository;

    @Override
    public ResponseEntity<?> getAll() {
        if (repository.count() != 0) {
            List<AbstractResponse> list = new ArrayList<>();
            repository.findAll().forEach(object -> {
                list.add(object.toResponse());
            });
            return ServerResponse.response(true, list, HttpStatus.OK);
        }

        return ServerResponse.response(false, "AgentSenders not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        if (repository.existsById(id)) {
            return ServerResponse.response(true, repository.findById(id).get().toResponse(), HttpStatus.OK);
        }

        return ServerResponse.response(false, "AgentSender not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> create(INDRequest request) {
        if (request.hasId()) {
            if (repository.existsById(request.getId())) {
                return ServerResponse.response(false, "AgentSender with id: " + request.getId() + " already exists!", HttpStatus.BAD_REQUEST);
            }
        }

        if (request.emptyValues().isEmpty()) {
            repository.save(new AgentSender(request.getId(), request.getName(), request.getDescription()));
            return ServerResponse.response(true, "AgentSender successful created", HttpStatus.OK);
        } else {
            return ServerResponse.response(false, "Some values are empty: " + request.emptyValues().toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> edit(Long id, INDRequest request) {
        if (repository.existsById(id)) {
            return ServerResponse.response(false, "AgentSender with id: " + request.getId() + " does not exist!", HttpStatus.NOT_FOUND);
        }
        if (request.hasId()) {
            if (repository.existsById(request.getId())) {
                return ServerResponse.response(false, "AgentSender with id: " + request.getId() + " already exists!", HttpStatus.BAD_REQUEST);
            }
        }

        AgentSender agentSender = repository.findById(id).get();
        agentSender.edit(request);
        repository.save(agentSender);

        return ServerResponse.response(true, "AgentSender edited successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ServerResponse.response(true, "AgentSender deleted successfully", HttpStatus.OK);
        } else {
            return ServerResponse.response(true, "AgentSender with id: " + id + " does not exist!", HttpStatus.BAD_REQUEST);
        }
    }
}
