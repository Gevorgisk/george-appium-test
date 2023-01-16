package com.george.tests

import com.george.base.AppiumSetup
import com.george.pageobjects.*
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created By Gevorg Iskandaryan
 * This test does first login and makes new transfer
 */
class FirstLoginAndNewTransferTest : AppiumSetup() {

    @Test
    fun loginViaPattern() {
        val homePage = HomeBasePage(driver!!, testUtils)
        val loginPage = LoginPage(driver!!, testUtils)
        val accessMethodPage = AccessMethodPage(driver, testUtils)

        homePage.waitForMainPage()

        homePage.clickLoginToGeorgeBtn()

        loginPage.waitAndClickLoginDemoUserBtn()

        loginPage.waitForLoginToGeorgePage()
        loginPage.waitAndClickSecondLoginButton()

        accessMethodPage.waitAccessMethodPage()
        accessMethodPage.clickPatterAccessMethod()

        val listOfBullets = listOf("1", "3", "8", "11")

        // call the drawPattern twice since the application requires it to be drawn twice for security purposes
        for (i in 1..2){
            accessMethodPage.drawPattern(listOfBullets)
        }

        accessMethodPage.waitForSetupSuccessPage()
        accessMethodPage.clickDoneAfterSetup()

        accessMethodPage.clickMaybeLaterBtn()
        accessMethodPage.clickNotNowBtn()
        accessMethodPage.clickProceedWoPermissionBtn()
        accessMethodPage.clickOnboardingDoneBtn()

    }

    @Test(dependsOnMethods = ["loginViaPattern"])
    fun makeNewTransfer(){
        var overviewPage = OverviewPage(driver, testUtils)
        var newTransferPage = NewTransferPage(driver, testUtils)

        overviewPage.waitForOverviewPage()
        val initialBalanceOfFirstCardView = Integer.parseInt(overviewPage.getBalanceOfFirstCardView()?.split(",")?.get(0)?.replace("\\p{Z}".toRegex(), ""))
        overviewPage.clickFirstNewTransferBtn()
        newTransferPage.waitForNewTransferPage()
        newTransferPage.waitForFirstAccountOwnTransfer()
        newTransferPage.clickFirstAccountOwnTransfer()
        newTransferPage.waitAndSendAmountInput("400")
        newTransferPage.waitAndClickActionNext()
        newTransferPage.waitAndClickSignBtn()
        newTransferPage.waitAndSendCodeInput("000000")
        newTransferPage.waitAndClickSubmitCodeBtn()
        newTransferPage.waitForThankYouMessage()
        newTransferPage.waitForConfirmMessage()
        newTransferPage.waitAndClickCloseBtnAfterTransfer()

        overviewPage.clickSorryNotInterestedBtn()
        overviewPage.waitForOverviewPage()

        val balanceAfterTransaction = Integer.parseInt(overviewPage.getBalanceOfFirstCardView()?.split(",")?.get(0)?.replace("\\p{Z}".toRegex(), ""))

//        validation
        Assert.assertEquals(balanceAfterTransaction,initialBalanceOfFirstCardView - 400,  "The balance after the transaction (400Kc) is not as expected, before: '"+
        + initialBalanceOfFirstCardView + "', after '" + balanceAfterTransaction + "'")

    }
}