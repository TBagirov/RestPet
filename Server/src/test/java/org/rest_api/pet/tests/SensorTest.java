package org.rest_api.pet.tests;

import org.junit.jupiter.api.Test;
import org.rest_api.pet.pages.HomePage;
import org.rest_api.pet.pages.SensorPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SensorTest extends BaseTest {

    @Test
    public void testSensorRegistration() {
        HomePage homePage = new HomePage(driver);
        SensorPage sensorPage = homePage.goToSensorPage();

        sensorPage.registerSensor("TestSensor");
        assertThat(sensorPage.isSensorDisplayed("TestSensor")).isTrue();
    }

    @Test
    public void testDuplicateSensorRegistration() {
        HomePage homePage = new HomePage(driver);
        SensorPage sensorPage = homePage.goToSensorPage();

        sensorPage.registerSensor("DuplicateSensor");
        sensorPage.registerSensor("DuplicateSensor");

        assertThat(sensorPage.isSensorDisplayed("DuplicateSensor")).isTrue();
    }

    @Test
    public void testSensorListDisplay() {
        HomePage homePage = new HomePage(driver);
        SensorPage sensorPage = homePage.goToSensorPage();

        assertThat(sensorPage.isSensorDisplayed("")).isTrue();
    }

}
