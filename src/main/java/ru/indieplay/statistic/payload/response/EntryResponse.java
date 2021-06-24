package ru.indieplay.statistic.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.indieplay.statistic.models.AgentSender;
import ru.indieplay.statistic.models.Event;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Map;

/**
 * Created by Fadesml on 23.05.2021.
 */

@Getter
@Setter
public class EntryResponse implements AbstractResponse {
    private final Long id;
    private final Long eventId;
    private final Long agentSenderId;
    private final String agentData;
    private final Map data;
    private final Date date;

    public EntryResponse(Long id, Long eventId, Long agentSenderId, String agentData, Map data, Date date) {
        this.id = id;
        this.eventId = eventId;
        this.agentSenderId = agentSenderId;
        this.agentData = agentData;
        this.data = data;
        this.date = date;
    }
}
