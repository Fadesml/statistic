package ru.indieplay.statistic.models;

import ru.indieplay.statistic.payload.request.AbstractRequest;
import ru.indieplay.statistic.payload.response.AbstractResponse;

/**
 * Created by Fadesml on 23.05.2021.
 */
public abstract class AbstractModel {
    public AbstractResponse toResponse() {
        return new AbstractResponse() {};
    }
    public void edit(AbstractRequest request) {}
}
