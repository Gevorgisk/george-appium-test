package com.george.tests

import com.george.base.LoginBaseTest
import com.george.pageobjects.NewTransferPage
import com.george.pageobjects.OverviewPage
import com.george.pageobjects.RecipientPage
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class FirstLoginNewManualTransferTest: LoginBaseTest() {
    @Test
    fun loginViaPattern() {
        val listOfBullets = listOf("1", "3", "8", "11")
        firstLoginWithPattern(listOfBullets)
    }

    @DataProvider(name = "transferInputs")
    fun testInputs(): Array<Array<Any>> {
        return arrayOf(
            arrayOf("Abala", "300", true, true),
            arrayOf("Brindzik", "0", false, false),
            arrayOf("Cip", "", false, false),
            arrayOf("Andrij", "80", false, true))
    }

    @Test(dependsOnMethods = ["loginViaPattern"], dataProvider = "transferInputs")
    fun makeNewManualTransfer(recipientName: String, amountToTransfer:String, sorryNotInterested:Boolean, isAmountPositive:Boolean){
        val overviewPage = OverviewPage(driver, testUtils)
        val newTransferPage = NewTransferPage(driver, testUtils)
        val recipientPage = RecipientPage(driver, testUtils)

        overviewPage.waitForOverviewPage()
        val initialBalanceOfFirstCardView = overviewPage.getBalanceOfFirstCardView()
        overviewPage.clickFirstNewTransferBtn()
        newTransferPage.waitForNewTransferPage()
        newTransferPage.clickManualTransferNode()
        recipientPage.waitAndInputRecipent(recipientName)
        recipientPage.waitAndClickRecipent(recipientName)
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
            for (i in 1..2){
                newTransferPage.clickBackBtn()
            }
            newTransferPage.clickNavigationUpBtn()
        }

    }

}