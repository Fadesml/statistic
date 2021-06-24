package ru.indieplay.statistic.payload.request;

import lombok.Getter;
import lombok.Setter;
import ru.indieplay.statistic.models.AgentSender;
import ru.indieplay.statistic.models.Event;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Fadesml on 23.05.2021.
 */

@Getter
@Setter
public class EntryRequest implements AbstractRequest{
    private Long eventId;
    private Long agentSenderId;
    private String agentData;
    private Map data;

    @Override
    public List<String> emptyValues() {
        List<String> result = new ArrayList<>();
        if (this.eventId == null)  result.add("Event Id");
        if (this.agentSenderId == null) result.add("Agent Sender Id");
        if (this.agentData == null) result.add("Agent Data");

        return result;
    }
}
