package ru.indieplay.statistic.services;

import org.springframework.http.ResponseEntity;
import ru.indieplay.statistic.payload.request.INDRequest;

/**
 * Created by Fadesml on 23.05.2021.
 */
public interface IAgentSenderService extends AbstractService{
    @Override
    ResponseEntity<?> getAll();

    @Override
    ResponseEntity<?> getById(Long id);

    @Override
    ResponseEntity<?> create(INDRequest request);

    @Override
    ResponseEntity<?> edit(Long id, INDRequest request);

    @Override
    ResponseEntity<?> delete(Long id);
}
