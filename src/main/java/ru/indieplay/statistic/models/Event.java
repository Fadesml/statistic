package ru.indieplay.statistic.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.indieplay.statistic.payload.request.AbstractRequest;
import ru.indieplay.statistic.payload.request.INDRequest;
import ru.indieplay.statistic.payload.response.AbstractResponse;
import ru.indieplay.statistic.payload.response.EntryResponse;
import ru.indieplay.statistic.payload.response.INDResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Fadesml on 23.05.2021.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy="event")
    private Set<Entry> entries;

    public Event(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public INDResponse toResponse() {
        List<EntryResponse> entries = new ArrayList<>();
        this.entries.forEach(entry -> entries.add((EntryResponse) entry.toResponse()));
        return new INDResponse(this.id, this.name, this.description, entries);
    }

    @Override
    public void edit(AbstractRequest request) {
        INDRequest indRequest = (INDRequest) request;
        if (indRequest.hasId()) this.id = indRequest.getId();
        if (indRequest.getDescription() != null) this.description = indRequest.getDescription();
        if (indRequest.getName() != null) this.name = indRequest.getName();
    }
}
