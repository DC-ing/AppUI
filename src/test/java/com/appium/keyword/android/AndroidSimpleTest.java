package com.appium.keyword.android;

import com.appium.keyword.Engine_Excel;
import com.appium.locator.AndroidLocator;
import com.appium.locator.InitLocator;
import com.framework.utils.ConfigManager;
import com.framework.utils.ExcelUtils;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class AndroidSimpleTest {
	
	protected AndroidLocator<MobileElement> locator;
	protected ExcelUtils excelUtils;
	protected Engine_Excel engine;
	
	@BeforeTest
	@Parameters({"deviceNum"})
	public void beforeTest(int deviceNum) throws IOException {
		String androidExcelTestPath = ConfigManager.getAndroidExcelName();
		excelUtils = new ExcelUtils(androidExcelTestPath);
		engine = new Engine_Excel(androidExcelTestPath);
		locator = new InitLocator().getAndroidLocator(excelUtils.getAndroidDeviceTestConfig(deviceNum));
	}

	@Test
	public void test() {
		engine.runTest(new MainKeyWord(locator));
	}
	
	@AfterTest
	public void afterTest() {
		locator.quit();
	}
}
