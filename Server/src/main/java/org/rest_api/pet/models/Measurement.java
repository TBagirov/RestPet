package org.rest_api.pet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="value")
    @Min(value=-100, message = "The minimum air temperature cannot be lower than -100")
    @Max(value=100, message = "The maximum air temperature cannot be higher than 100")
    private float value;

    @Column(name="raining")
    @NotNull(message = "Rain registration cannot be omitted")
    private Boolean raining;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    @NotNull(message = "Measurements cannot contain sensor data")
    private Sensor sensor;

    public Measurement(float value, boolean raining, Sensor sensor, LocalDateTime createdAt) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.createdAt = createdAt;
    }

}
