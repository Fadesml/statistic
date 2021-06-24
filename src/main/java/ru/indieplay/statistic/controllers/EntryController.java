package ru.indieplay.statistic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.indieplay.statistic.payload.request.EntryRequest;
import ru.indieplay.statistic.services.IEntryService;

/**
 * Created by Fadesml on 23.05.2021.
 */

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/statistic")
public class EntryController {
    @Autowired
    private IEntryService entryService;

    @PostMapping("/")
    public ResponseEntity<?> createEntry(@RequestBody EntryRequest request) {
        return entryService.create(request);
    }
}
