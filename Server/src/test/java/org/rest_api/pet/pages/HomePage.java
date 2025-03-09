package org.rest_api.pet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "sensorPageLink")
    private WebElement sensorPageLink;

    @FindBy(id = "measurementPageLink")
    private WebElement measurementPageLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SensorPage goToSensorPage() {
        sensorPageLink.click();
        return new SensorPage(driver);
    }

    public MeasurementPage goToMeasurementPage() {
        measurementPageLink.click();
        return new MeasurementPage(driver);
    }
}
