package org.rest_api.pet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SensorPage extends BasePage {

    @FindBy(id = "sensorNameInput")
    private WebElement sensorNameInput;

    @FindBy(id = "registerSensorButton")
    private WebElement registerSensorButton;

    @FindBy(id = "sensorList")
    private WebElement sensorList;

    public SensorPage(WebDriver driver) {
        super(driver);
    }

    public void registerSensor(String name) {
        sensorNameInput.sendKeys(name);
        registerSensorButton.click();
    }

    public boolean isSensorDisplayed(String name) {
        return sensorList.getText().contains(name);
    }
}
