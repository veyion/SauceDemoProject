package com.qa.saucedemo.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.saucedemo.utils.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {


		WebDriver driver;
		public Properties prop;
		//public ElementUtil elementUtil;
		public String browserName;
		public ElementUtil elementUtil;


		public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

		public static synchronized WebDriver getDriver() {
			return tlDriver.get();
		}

		/**
		 * this method is used to initialize the WebDriver on the basis of browser
		 * 
		 * @param browserName
		 * @return driver
		 */
		public WebDriver init_driver(/*Properties prop*/) {
		// just made it default chromedriver
			
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			prop =init_prop();
			getDriver().get(prop.getProperty("url"));
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().fullscreen();
			getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			getDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			
			return getDriver();
		}
		
		public Properties init_prop() {
			prop = new Properties();
			try {
				FileInputStream ip =new FileInputStream("./src/main/java/com/qa/saucedemo/base/qa.config.properties");
		      prop.load(ip);
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
			return prop;
			
		}

		
		public String getScreenshot() {

			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return path;

		}

	}

