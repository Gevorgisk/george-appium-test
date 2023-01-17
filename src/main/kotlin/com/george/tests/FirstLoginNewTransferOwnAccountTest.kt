package com.george.tests

import com.george.base.LoginBaseTest
import com.george.pageobjects.*
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

/**
 * Created By Gevorg Iskandaryan
 * This test does first login and makes new transfer
 */
class FirstLoginNewTransferOwnAccountTest : LoginBaseTest() {

    @Test
    fun loginViaPattern() {
        val listOfBullets = listOf("1", "3", "8", "11")
        firstLoginWithPattern(listOfBullets)
    }

    @DataProvider(name = "transferInputs")
    fun testInputs(): Array<Array<Any>> {
        return arrayOf(
            arrayOf("400", true, true),
            arrayOf("0", false, false),
            arrayOf("", false, false),
            arrayOf("40", false, true))
    }
    @Test(dependsOnMethods = ["loginViaPattern"], dataProvider = "transferInputs")
    fun makeNewTransfer(amountToTransfer:String, sorryNotInterested:Boolean, isAmountPositive:Boolean){
        val overviewPage = OverviewPage(driver, testUtils)
        val newTransferPage = NewTransferPage(driver, testUtils)

        overviewPage.waitForOverviewPage()
        val initialBalanceOfFirstCardView = overviewPage.getBalanceOfFirstCardView()
        overviewPage.clickFirstNewTransferBtn()
        newTransferPage.waitForNewTransferPage()
        newTransferPage.waitForFirstAccountOwnTransfer()
        newTransferPage.clickFirstAccountOwnTransfer()
        newTransferPage.waitAndSendAmountInput(amountToTransfer)
        if (isAmountPositive){
            newTransferPage.waitAndClickActionNext()
            newTransferPage.waitAndClickSignBtn()
            newTransferPage.waitAndSendCodeInput("000000")
            newTransferPage.waitAndClickSubmitCodeBtn()
            newTransferPage.waitForThankYouMessage()
            newTransferPage.waitForConfirmMessage()
            newTransferPage.waitAndClickCloseBtnAfterTransfer()

            if (sorryNotInterested){
                overviewPage.clickSorryNotInterestedBtn()
            }
            overviewPage.waitForOverviewPage()

            val balanceAfterTransaction = overviewPage.getBalanceOfFirstCardView()

    //        validation
            // validate that the initial balance was reduced by X after the transfer
            Assert.assertEquals(balanceAfterTransaction,initialBalanceOfFirstCardView - Integer.parseInt(amountToTransfer),  "The balance after the transaction is not as expected, before: '"+
            + initialBalanceOfFirstCardView + "', after '" + balanceAfterTransaction + "'")

            // similarly we should validate that another card balance (where transferred to) is increased by 400
        } else {
            Assert.assertEquals(newTransferPage.isNextButtonsEnabled(), false,"Expected false but got true, Next button is enabled")
            newTransferPage.clickBackBtn()
            newTransferPage.clickNavigationUpBtn()
        }
    }
}