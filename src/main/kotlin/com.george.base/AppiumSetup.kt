package com.george.base

import com.george.utils.TestUtils
import org.openqa.selenium.remote.DesiredCapabilities
import io.appium.java_client.android.AndroidDriver
import java.net.URL
import java.nio.file.Paths
import io.appium.java_client.AppiumDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.io.FileInputStream
import java.util.*

/**
 * Created by Gevorg Iskandaryan.
 * Base test class for Appium initialisation
 */
open class AppiumSetup: TestUtils() {

    lateinit var driver: AppiumDriver
    lateinit var testUtils: TestUtils
    private val testProps = "src/main/resources/main.test.properties"

    @BeforeClass
    fun setup() {
        val prop = Properties()
        FileInputStream(testProps).use{ prop.load(it) }

        val capabilities = DesiredCapabilities()
        val userDir = System.getProperty("user.dir")
        val serverAddress = URL(prop.getProperty("server.address"))
        testUtils = TestUtils()

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android")

        val localApp = prop.getProperty("local.app")
        val appPath = Paths.get(userDir, localApp).toAbsolutePath().toString()
        capabilities.setCapability(MobileCapabilityType.APP, appPath)

        driver = AndroidDriver(serverAddress, capabilities)
    }

    @AfterClass
    fun tearDown() {
        driver.quit()
    }
}