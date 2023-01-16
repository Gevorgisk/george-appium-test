package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.time.Duration

class RecipientPage(driver: AppiumDriver, testUtils: TestUtils): BasePage(driver, testUtils) {

    @AndroidFindBy(id = "search_input")
    private var searchInput: WebElement? = null

    val recipentNodeXpath = "//android.widget.TextView[@content-desc='recipent_pattern']"

    fun waitAndInputRecipent(inputRecipent: String){
        testUtils.waitForWebElement(driver, searchInput)
        searchInput?.sendKeys(inputRecipent)
    }

    fun waitAndClickRecipent(recipentName: String){
        testUtils.waitForWebElementBy(driver, By.xpath(recipentNodeXpath.replace("recipent_pattern", recipentName)), Duration.ofSeconds(30)).click()
    }

}