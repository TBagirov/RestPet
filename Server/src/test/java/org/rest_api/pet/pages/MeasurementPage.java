package org.rest_api.pet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MeasurementPage extends BasePage {

    @FindBy(id = "sensorNameInput")
    private WebElement sensorNameInput;

    @FindBy(id = "measurementValueInput")
    private WebElement measurementValueInput;

    @FindBy(id = "isRainingCheckbox")
    private WebElement isRainingCheckbox;

    @FindBy(id = "addMeasurementButton")
    private WebElement addMeasurementButton;

    @FindBy(id = "measurementList")
    private WebElement measurementList;

    public MeasurementPage(WebDriver driver) {
        super(driver);
    }

    public void addMeasurement(String sensorName, String value, boolean isRaining) {
        sensorNameInput.sendKeys(sensorName);
        measurementValueInput.sendKeys(value);
        if (isRaining) {
            isRainingCheckbox.click();
        }
        addMeasurementButton.click();
    }

    public boolean isMeasurementDisplayed(String value) {
        return measurementList.getText().contains(value);
    }
}
