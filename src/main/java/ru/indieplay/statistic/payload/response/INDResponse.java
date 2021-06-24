package ru.indieplay.statistic.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Fadesml on 23.05.2021.
 */

@Getter
@Setter
@AllArgsConstructor
public class INDResponse implements AbstractResponse {
    private Long id;
    private String name;
    private String description;
    private List<EntryResponse> entries;
}
