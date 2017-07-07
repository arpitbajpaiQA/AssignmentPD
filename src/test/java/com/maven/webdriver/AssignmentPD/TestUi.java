package com.maven.webdriver.AssignmentPD;

import org.testng.annotations.Test;

import junit.framework.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

public class TestUi {
	
	static WebDriver driver = null;
	Properties prop = new Properties();

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\arpitbajpai\\Downloads\\chromedriver.exe");
		 driver = new ChromeDriver();
		// System.setProperty("webdriver.gecko.driver",
		// "C:\\Users\\arpitbajpai\\Downloads\\geckodriver.exe");
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			System.out.println(prop.getProperty("name"));
			System.out.println(prop.getProperty("idate"));
			System.out.println(prop.getProperty("ddate"));
			System.out.println(prop.getProperty("company"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		String url = "http://computer-database.gatling.io/computers";
		driver.get(url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void f() {
		//TestUi ui = new TestUi();
		driver.findElement(By.cssSelector("#add")).click();
		driver.findElement(By.cssSelector("#name")).sendKeys(prop.getProperty("name"));
		driver.findElement(By.cssSelector("#introduced")).sendKeys(prop.getProperty("idate"));
		driver.findElement(By.cssSelector("#discontinued")).sendKeys(prop.getProperty("ddate"));
		Select dropdown = new Select(driver.findElement(By.cssSelector("#company")));
		dropdown.selectByVisibleText(prop.getProperty("company"));
		//driver.findElement(By.cssSelector("#company>option[value='3']")).click();
		 driver.findElement(By.cssSelector("#main > form > div > input")).click();
		driver.findElement(By.cssSelector("#searchbox")).sendKeys("zzz");
		driver.findElement(By.cssSelector("#searchbox")).sendKeys(Keys.ENTER);
		 driver.findElement(By.cssSelector("#main > table > tbody > tr > td:nth-child(1) > a")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#name")).getAttribute("value"), prop.getProperty("name"));
		Assert.assertEquals(driver.findElement(By.cssSelector("#introduced")).getAttribute("value"), prop.getProperty("idate"));
		Assert.assertEquals(driver.findElement(By.cssSelector("#discontinued")).getAttribute("value"), prop.getProperty("ddate"));
		String text= new Select( driver.findElement(By.cssSelector("#company"))).getFirstSelectedOption().getText();
		Assert.assertEquals(text , prop.getProperty("company"));
		
	}
}
