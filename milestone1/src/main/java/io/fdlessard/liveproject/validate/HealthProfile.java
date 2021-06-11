package io.fdlessard.liveproject.validate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HealthProfile {

    private int id;

    private String username;

    @JsonIgnore
    private List<HealthMetric> metrics;


}
