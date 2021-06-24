package ru.indieplay.statistic.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hibernate.annotations.Type;
import ru.indieplay.statistic.payload.request.AbstractRequest;
import ru.indieplay.statistic.payload.response.AbstractResponse;
import ru.indieplay.statistic.payload.response.EntryResponse;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by Fadesml on 23.05.2021.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "entries")
public class Entry extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="agent_sender_id", nullable=false)
    private AgentSender agentSender;
    private String agentData;
    private String data;
    private Date date;

    @ManyToOne
    @JoinColumn(name="event_id", nullable=false)
    private Event event;

    public Entry(AgentSender agentSender, String agentData, String data, Event event) {
        this.agentSender = agentSender;
        this.agentData = agentData;
        this.data = data;
        this.event = event;
        this.date = new Date();
    }

    @SneakyThrows
    @Override
    public AbstractResponse toResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map dataMap = objectMapper.readValue(this.data, Map.class);
        return new EntryResponse(
                this.id,
                this.event.getId(),
                this.agentSender.getId(),
                this.agentData,
                dataMap,
                this.date);
    }

    @Override
    public void edit(AbstractRequest request) {
        //Method not intended
    }
}
