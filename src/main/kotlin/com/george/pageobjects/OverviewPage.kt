package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

/**
 * Created by Gevorg Iskandaryan
 * Overview Page
 */
class OverviewPage(driver: AppiumDriver, testUtils: TestUtils): BasePage(driver, testUtils) {

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Overview']")
    private var overviewHeader: WebElement? = null

    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView//android.widget.Button[@text='New Transfer'][1]")
    private var firstNewTransferButton: WebElement? = null

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='cz.csas.georgego:id/balance'][1]")
    private var firstCardViewBalance: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.TextView' and contains(@text, 'Sorry, not interested')]")
    private var sorryNotInterestedButton: WebElement? = null

    @AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='cz.csas.georgego:id/action_contact']")
    private var contactBtn: WebElement? = null


    fun waitForOverviewPage(){
        testUtils.waitForWebElement(driver, overviewHeader)
    }

    fun clickFirstNewTransferBtn(){
        firstNewTransferButton?.click()
    }


    fun  getBalanceOfFirstCardView(): Int {
        return Integer.parseInt(firstCardViewBalance?.text?.split(",")?.get(0)?.replace("\\p{Z}".toRegex(), ""))
    }

    fun clickSorryNotInterestedBtn(){
        sorryNotInterestedButton?.click()
    }

    fun clickContactBtn(){
        contactBtn?.click()
    }

}