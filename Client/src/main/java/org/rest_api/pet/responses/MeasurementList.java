package org.rest_api.pet.responses;

import org.rest_api.pet.models.Measurement;

import java.util.List;

public class MeasurementList {
    List<Measurement> measurementList;

    public MeasurementList(){}
    public MeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
    }

    public List<Measurement> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
    }


}
