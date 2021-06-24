package ru.indieplay.statistic.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fadesml on 23.05.2021.
 */
@Getter
@Setter
public class INDRequest implements AbstractRequest {
    private Long id;
    private String name;
    private String description;

    public boolean hasId() {
        return id != null;
    }

    @Override
    public List<String> emptyValues() {
        List<String> result = new ArrayList<>();
        if (this.id == null)  result.add("Id");
        if (this.name == null) result.add("Name");
        if (this.description == null) result.add("Description");

        return result;
    }

    @Override
    public String toString() {
        return "INDRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
