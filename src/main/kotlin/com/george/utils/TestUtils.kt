package com.george.utils

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

open class TestUtils {

    fun waitForWebElement(driver: AppiumDriver, element: WebElement?, timeout: Duration){
        val wait = WebDriverWait(driver, timeout)
        wait.until(ExpectedConditions.visibilityOf(element))
    }

    fun waitForWebElement(driver: AppiumDriver, element: WebElement?){
        val timeout = Duration.ofSeconds(30)
        waitForWebElement(driver, element, timeout)
    }

    fun waitForWebElementBy(driver: AppiumDriver, by: By?, timeout: Duration):WebElement{
        val wait = WebDriverWait(driver, timeout)
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by))
    }



}