package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class ContactPage(driver: AppiumDriver, testUtils: TestUtils): BasePage(driver, testUtils) {

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Contact']")
    private var contactPageHeader: WebElement? = null

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Messages']")
    private var messagesNode: WebElement? = null

    @AndroidFindBy(xpath = "//*[@resource-id='cz.csas.georgego:id/new_message']")
    private var newMessageBtn: WebElement? = null

    fun waitForContactPage(){
        testUtils.waitForWebElement(driver, contactPageHeader)
    }

    fun clickMessagesNode(){
        messagesNode?.click()
    }

    fun waitAndClickNewMessageBtn(){
        testUtils.waitForWebElement(driver, newMessageBtn)
        newMessageBtn?.click()
    }

}