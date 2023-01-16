package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory

/**
 * Created by Gevorg Iskandaryan.
 * Base page object
 */
abstract class BasePage(val driver: AppiumDriver, val testUtils: TestUtils) {
    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }
}