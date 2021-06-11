package io.fdlessard.liveproject.validate;

import io.fdlessard.liveproject.validate.enums.HealthMetricType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HealthMetric {

    private int id;

    private double value;

    private HealthMetricType type;

    private HealthProfile profile;


}
