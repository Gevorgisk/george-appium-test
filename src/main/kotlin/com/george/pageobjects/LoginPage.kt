package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

/**
 * Created by Gevorg Iskandaryan.
 * Login page
 */
class LoginPage(driver: AppiumDriver, testUtils: TestUtils): BasePage(driver, testUtils) {
    @AndroidFindBy(id="btn_login_with_demo_user")
    private var loginDemoUserBtn: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.Button' and contains(@text, 'Login')]")
    private var loginDemoUserNextBtn: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.Button' and contains(@text, 'Cancel')]")
    private var loginToGeorgeCancelBtn: WebElement? = null

    fun waitAndClickLoginDemoUserBtn(){
        testUtils.waitForWebElement(driver, loginDemoUserBtn)
        loginDemoUserBtn?.click()
    }

    fun waitAndClickSecondLoginButton(){
        testUtils.waitForWebElement(driver, loginDemoUserNextBtn)
        loginDemoUserNextBtn?.click()
    }

    fun waitForLoginToGeorgePage(){
        testUtils.waitForWebElement(driver,loginToGeorgeCancelBtn)
    }

}