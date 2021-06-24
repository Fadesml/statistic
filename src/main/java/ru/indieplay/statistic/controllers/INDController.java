package ru.indieplay.statistic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.indieplay.statistic.payload.request.INDRequest;
import ru.indieplay.statistic.payload.response.ServerResponse;
import ru.indieplay.statistic.services.AbstractService;
import ru.indieplay.statistic.services.IAgentSenderService;
import ru.indieplay.statistic.services.IEntryService;
import ru.indieplay.statistic.services.IEventService;
import ru.indieplay.statistic.services.impl.EventServiceImpl;

/**
 * Created by Fadesml on 23.05.2021.
 */

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/statistic/management")
public class INDController {
    @Autowired
    private IEntryService entryService;
    
    @Autowired
    private IEventService eventService;
    
    @Autowired
    private IAgentSenderService agentSenderService;
    
    public AbstractService getService(String name) {
        if (name.equals("event")) { return eventService; }
        else if (name.equals("agentsender")) { return agentSenderService; }
        else { return new AbstractService() {
            private ResponseEntity<?> failed = ServerResponse.response(false, "Service with name: " + name + " not found!", HttpStatus.BAD_REQUEST);
            @Override
            public ResponseEntity<?> getAll() {
                return failed;
            }

            @Override
            public ResponseEntity<?> getById(Long id) {
                return failed;
            }

            @Override
            public ResponseEntity<?> create(INDRequest request) {
                return failed;
            }

            @Override
            public ResponseEntity<?> edit(Long id, INDRequest request) {
                return failed;
            }

            @Override
            public ResponseEntity<?> delete(Long id) {
                return failed;
            }
        };
        }
    }
    

    @GetMapping("/ind/{name}")
    public ResponseEntity<?> getAll(@PathVariable String name) {
        return getService(name).getAll();
    }

    @GetMapping("/ind/{name}/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id, @PathVariable String name) {
        return getService(name).getById(id);
    }

    @PostMapping("/ind/{name}")
    public ResponseEntity<?> create(@PathVariable String name, @RequestBody INDRequest request) {
        return getService(name).create(request);
    }

    @PutMapping("/ind/{name}/{id}")
    public ResponseEntity<?> edit(@PathVariable String name, @PathVariable Long id, @RequestBody INDRequest request) {
        return getService(name).edit(id, request);
    }

    @DeleteMapping("/ind/{name}/{id}")
    public ResponseEntity<?> delete(@PathVariable String name, @PathVariable Long id) {
        return getService(name).delete(id);
    }

    @DeleteMapping("/entry")
    public ResponseEntity<?> deleteAll() {
        return entryService.deleteAll();
    }

    @DeleteMapping("/entry/by/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return entryService.deleteById(id);
    }

    @DeleteMapping("/entry/by/event/{id}")
    public ResponseEntity<?> deleteAllByEvent(@PathVariable Long id) {
        return entryService.deleteAllByEvent(id);
    }

    @DeleteMapping("/entry/by/agentsender/{id}")
    public ResponseEntity<?> deleteAllByAgentSender(@PathVariable Long id) {
        return entryService.deleteAllByAgentSender(id);
    }
}
