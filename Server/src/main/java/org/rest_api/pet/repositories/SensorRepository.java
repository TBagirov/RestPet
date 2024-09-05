package org.rest_api.pet.repositories;

import org.rest_api.pet.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public Sensor findByName(String name);
}
