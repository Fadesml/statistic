package ru.indieplay.statistic.services;

import org.springframework.http.ResponseEntity;
import ru.indieplay.statistic.payload.request.INDRequest;

/**
 * Created by Fadesml on 23.05.2021.
 */
public interface AbstractService {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> create(INDRequest request);
    ResponseEntity<?> edit(Long id, INDRequest request);
    ResponseEntity<?> delete(Long id);
}
