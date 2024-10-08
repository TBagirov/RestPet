package org.rest_api.pet.services;

import org.rest_api.pet.models.Sensor;
import org.rest_api.pet.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id){
        return sensorRepository.findById(id).orElse(null);
    }

    public Optional<Sensor> findByName(String name){
        return Optional.ofNullable(sensorRepository.findByName(name));
    }

    @Transactional
    public void save(Sensor sensor){

        sensorRepository.save(sensor);
    }



}
