package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

/**
 * Created By Gevorg Iskandaryan
 * access method page
 */
class AccessMethodPage(driver: AppiumDriver, testUtils: TestUtils): BasePage(driver, testUtils) {
    @AndroidFindBy(id = "erste_locker_tv_choose_ea_title")
    var accessMethodHeader: WebElement? = null

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Pattern. Unlock the app by using a pattern.']")
    var patternButton: WebElement? = null

    @AndroidFindBy(id = "erste_locker_tv_title")
    private var setupSuccessHeader: WebElement?=null

    @AndroidFindBy(id = "erste_locker_btn_top")
    private var doneBtnSetupSuccess: WebElement?=null

    @AndroidFindBy(xpath="//*[@class='android.widget.Button' and contains(@text, 'Maybe later')]")
    private var maybeLaterBtn: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.Button' and contains(@text, 'Not now')]")
    private var notNowBtn: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.TextView' and contains(@text, 'Yes, proceed without permission')]")
    private var proceedWoPermissionBtn: WebElement? = null

    @AndroidFindBy(xpath="//*[@class='android.widget.Button' and contains(@text, 'Done')]")
    private var onboardingDoneBtn: WebElement? = null


    fun drawPattern(bullets: List<String>){
        val xpathExpression = "//hierarchy//android.view.View/android.view.View[index]"
        val action = Actions(driver)
        for (i in bullets.indices){
            val currentBullet = bullets[i]
            if (i==0){
                action.moveToElement(driver.findElement(By.xpath(xpathExpression.replace("index", currentBullet)))).clickAndHold()
            } else {
                action.moveToElement(driver.findElement(By.xpath(xpathExpression.replace("index", currentBullet))))
            }
        }
        action.release().perform()
    }

    fun waitAccessMethodPage(){
        testUtils.waitForWebElement(driver, accessMethodHeader)
    }

    fun clickPatterAccessMethod(){
        patternButton?.click()
    }

    fun waitForSetupSuccessPage(){
        testUtils.waitForWebElement(driver, setupSuccessHeader)
    }

    fun clickDoneAfterSetup(){
        testUtils.waitForWebElement(driver, doneBtnSetupSuccess)
        doneBtnSetupSuccess?.click()
    }

    fun clickMaybeLaterBtn(){
        testUtils.waitForWebElement(driver, maybeLaterBtn)
        maybeLaterBtn?.click()

    }
    fun clickNotNowBtn(){
        testUtils.waitForWebElement(driver, notNowBtn)
        notNowBtn?.click()
    }

    fun clickProceedWoPermissionBtn(){
        testUtils.waitForWebElement(driver, proceedWoPermissionBtn)
        proceedWoPermissionBtn?.click()
    }

    fun clickOnboardingDoneBtn(){
        testUtils.waitForWebElement(driver, onboardingDoneBtn)
        onboardingDoneBtn?.click()
    }


}