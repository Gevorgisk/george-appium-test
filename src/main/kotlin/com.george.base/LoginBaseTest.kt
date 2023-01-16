package com.george.base

import com.george.pageobjects.AccessMethodPage
import com.george.pageobjects.HomeBasePage
import com.george.pageobjects.LoginPage

open class LoginBaseTest: AppiumSetup() {

    fun firstLoginWithPattern(listOfBullets: List<String>){
        val homePage = HomeBasePage(driver, testUtils)
        val loginPage = LoginPage(driver, testUtils)
        val accessMethodPage = AccessMethodPage(driver, testUtils)

        homePage.waitForMainPage()

        homePage.clickLoginToGeorgeBtn()

        loginPage.waitAndClickLoginDemoUserBtn()

        loginPage.waitForLoginToGeorgePage()
        loginPage.waitAndClickSecondLoginButton()

        accessMethodPage.waitAccessMethodPage()
        accessMethodPage.clickPatterAccessMethod()

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

}