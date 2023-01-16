package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.*
import org.openqa.selenium.WebElement

/**
 * Created by Gevorg Iskandaryan.
 * Home page
 */
class HomeBasePage(driver: AppiumDriver, testUtils: TestUtils) : BasePage(driver, testUtils) {

    @AndroidFindBy(id="kyc_tour_login_btn")
    var loginToGeorgeButton: WebElement? = null

    fun waitForMainPage(){
        testUtils.waitForWebElement(driver, loginToGeorgeButton)
    }

    fun clickLoginToGeorgeBtn(){
        loginToGeorgeButton?.click()
    }

}