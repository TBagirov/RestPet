package org.rest_api.pet.tests;

import org.junit.jupiter.api.Test;
import org.rest_api.pet.pages.HomePage;
import org.rest_api.pet.pages.MeasurementPage;
import org.rest_api.pet.pages.SensorPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MeasurementTest extends BaseTest {

    @Test
    public void testMeasurementAddition() {
        HomePage homePage = new HomePage(driver);
        SensorPage sensorPage = homePage.goToSensorPage();
        sensorPage.registerSensor("MeasurementSensor");

        MeasurementPage measurementPage = homePage.goToMeasurementPage();
        measurementPage.addMeasurement("MeasurementSensor", "23.5", true);

        assertThat(measurementPage.isMeasurementDisplayed("23.5")).isTrue();
    }

    @Test
    public void testMeasurementListDisplay() {
        HomePage homePage = new HomePage(driver);
        MeasurementPage measurementPage = homePage.goToMeasurementPage();

        assertThat(measurementPage.isMeasurementDisplayed("")).isTrue();
    }

    @Test
    public void testRainyDaysCount() {
        HomePage homePage = new HomePage(driver);
        MeasurementPage measurementPage = homePage.goToMeasurementPage();

        measurementPage.addMeasurement("RainSensor", "20.0", true);
        measurementPage.addMeasurement("RainSensor", "22.0", false);

        assertThat(measurementPage.isMeasurementDisplayed("20.0")).isTrue();
        assertThat(measurementPage.isMeasurementDisplayed("22.0")).isTrue();
    }

}
