package com.george.tests

import com.george.base.LoginBaseTest
import com.george.pageobjects.ContactPage
import com.george.pageobjects.NewMessagePage
import com.george.pageobjects.NewTransferPage
import com.george.pageobjects.OverviewPage
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class NewMessageTest: LoginBaseTest() {

    @DataProvider(name = "messageInputs")
    fun testInputs(): Array<Array<Any>> {
        return arrayOf(
            arrayOf("amazing_topic",  listOf("a", "b", "ccc", "d dd")),
            arrayOf("wonderful_topic",  listOf("a a", "b *&()*)(*)_", "c c c", "  s  d d d  ", "<html>"))
        )
    }

    @Test
    fun loginViaPattern() {
        val contactPage = ContactPage(driver, testUtils)
        val overviewPage = OverviewPage(driver, testUtils)
        val listOfBullets = listOf("1", "3", "8", "11")
        firstLoginWithPattern(listOfBullets)
        overviewPage.waitForOverviewPage()
        overviewPage.clickContactBtn()
        contactPage.waitForContactPage()
        contactPage.clickMessagesNode()
    }

    @Test(dependsOnMethods = ["loginViaPattern"], dataProvider = "messageInputs")
    fun topicMessageTest(topic: String, messages: List<String>){
        val newMessagePage = NewMessagePage(driver, testUtils)
        val newTransferPage = NewTransferPage(driver, testUtils)
        val contactPage = ContactPage(driver, testUtils)
        contactPage.waitAndClickNewMessageBtn()
        newMessagePage.inputMessageTopic(topic)
        newTransferPage.waitAndClickActionNext() // this method should be moved to a general/base page object
        newMessagePage.waitForNewMessageWindowByTopic(topic) // here we validate the topic representation in UI

        for (i in messages.indices) {
            newMessagePage.inputNewMessage(messages[i])
            newMessagePage.sendMessage()
            // validate the message is posted in the chat, currently there is a bug (or the feature is disabled) that the messages are not visible in the chat
        }
        newTransferPage.clickBackBtn()
    }
}