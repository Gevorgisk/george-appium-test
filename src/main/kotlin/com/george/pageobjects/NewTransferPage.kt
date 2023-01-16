package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

/**
 * Created By Gevorg Iskandaryan
 * New Transfer page
 */
class NewTransferPage(driver: AppiumDriver, testUtils: TestUtils): BasePage(driver, testUtils) {

    @AndroidFindBy(xpath="//androidx.cardview.widget.CardView[2]/android.view.ViewGroup")
    private var transferOwnFirstAccountPanel: WebElement? = null

    @AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id, 'subtitle')]")
    private var newTransferPageHookLocator: WebElement? = null

    @AndroidFindBy(id = "amount_input")
    private var amountInput: WebElement? = null

    @AndroidFindBy(id = "code_input")
    private var codeInput: WebElement? = null

    @AndroidFindBy(id = "action_next")
    private var actionNext: WebElement? = null

    @AndroidFindBy(id = "finish_transaction")
    private var signButton: WebElement? = null

    @AndroidFindBy(id = "btn_submit_code")
    private var submitCodeBtn: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.TextView' and contains(@text, 'Thank you')]")
    private var thankYouMessageAfterTransfer: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.TextView' and contains(@text, 'ou have signed your transfer')]")
    private var confirmMessageAfterTransfer: WebElement? = null

    @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Close']")
    private var afterTransferCloseBtn: WebElement? = null

    @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Back']")
    private var backbtn: WebElement? = null

    @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate up']")
    private var navigationUpBtn: WebElement? = null


    fun waitForFirstAccountOwnTransfer(){
        testUtils.waitForWebElement(driver, transferOwnFirstAccountPanel)
    }

    fun clickFirstAccountOwnTransfer(){
        transferOwnFirstAccountPanel?.click()
    }

    fun waitAndSendAmountInput(textToSend: String){
        testUtils.waitForWebElement(driver, amountInput)
        amountInput?.sendKeys(textToSend)
    }

    fun waitAndClickActionNext(){
        testUtils.waitForWebElement(driver, actionNext)
        actionNext?.click()
    }

    fun waitAndClickSignBtn(){
        testUtils.waitForWebElement(driver, signButton)
        signButton?.click()
    }

    fun waitAndSendCodeInput(codeToSend: String){
        testUtils.waitForWebElement(driver, codeInput)
        codeInput?.sendKeys(codeToSend)
    }

    fun waitForThankYouMessage(){
        testUtils.waitForWebElement(driver,thankYouMessageAfterTransfer)
    }

    fun waitForConfirmMessage(){
        testUtils.waitForWebElement(driver,confirmMessageAfterTransfer)
    }

    fun waitAndClickCloseBtnAfterTransfer(){
        testUtils.waitForWebElement(driver, afterTransferCloseBtn)
        afterTransferCloseBtn?.click()
    }

    fun waitAndClickSubmitCodeBtn(){
        testUtils.waitForWebElement(driver, submitCodeBtn)
        submitCodeBtn?.click()
    }

    fun waitForNewTransferPage(){
        testUtils.waitForWebElement(driver, newTransferPageHookLocator)
    }

    fun isNextButtonsEnabled(): Boolean {
        return actionNext?.getAttribute("enabled").toBoolean()
    }

    fun clickBackBtn(){
        testUtils.waitForWebElement(driver, backbtn)
        backbtn?.click()
    }

    fun clickNavigationUpBtn(){
        testUtils.waitForWebElement(driver,navigationUpBtn)
        navigationUpBtn?.click()
    }


}