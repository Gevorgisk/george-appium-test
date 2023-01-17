package com.george.pageobjects

import com.george.utils.TestUtils
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.time.Duration

class NewMessagePage(driver: AppiumDriver, testUtils: TestUtils): BasePage(driver, testUtils)  {
    val topicInNewMessageWindow = "//*[@resource-id='cz.csas.georgego:id/subtitle' and @text='topic_pattern']"

    @AndroidFindBy(xpath="//*[@resource-id='cz.csas.georgego:id/messengerSubjectEditView']//android.widget.EditText")
    private var messageTopicInput: WebElement? = null

    @AndroidFindBy(xpath="//*[@resource-id='cz.csas.georgego:id/messageEditText']")
    private var messageTextInput: WebElement? = null

    @AndroidFindBy(xpath="//*[@resource-id='cz.csas.georgego:id/messageSendImageButton']")
    private var messageSendButton: WebElement? = null

    fun waitForNewMessageWindowByTopic(topicName: String){
        testUtils.waitForWebElementBy(driver, By.xpath(topicInNewMessageWindow.replace("topic_pattern", topicName)), Duration.ofSeconds(30))
    }

    fun inputMessageTopic(topic: String){
        testUtils.waitForWebElement(driver, messageTopicInput)
        messageTopicInput?.sendKeys(topic)
    }

    fun inputNewMessage(message: String){
        testUtils.waitForWebElement(driver, messageTextInput)
        messageTextInput?.sendKeys(message)
    }

    fun sendMessage(){
        messageSendButton?.click()
    }

}