package com.katalon.DemoProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Katalon_DemoProject {

	public static void main(String[] args) {
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--start-maximized");
		ChromeDriver driver=new ChromeDriver(option);
		
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.findElement(By.cssSelector("#btn-make-appointment")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("https://katalon-demo-cura.herokuapp.com/profile.php#login"));
		
		driver.findElement(By.xpath("//input[@id=\"txt-username\"]")).sendKeys("John Doe");
		driver.findElement(By.cssSelector("#txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.cssSelector("#btn-login")).click();
		
		wait.until(ExpectedConditions.urlContains("https://katalon-demo-cura.herokuapp.com/#appointment"));
	
	List<WebElement> options=driver.findElements(By.xpath("//select[@id=\"combo_facility\"]/option"));
	for(WebElement e: options) {
		String text=e.getText();
		if(text.equalsIgnoreCase("Hongkong CURA Healthcare Center")) {
			e.click();
			break;
		}
	}
	
	driver.findElement(By.xpath("//label[@for=\"chk_hospotal_readmission\"]/input")).click();
	driver.findElement(By.cssSelector("#txt_visit_date")).sendKeys("24/01/2024");
	driver.findElement(By.xpath("//div[@class=\"col-sm-4\"]/textarea")).sendKeys("Hello I want to meet");
	driver.findElement(By.xpath("//button[@id=\"btn-book-appointment\"]")).click();
	
	WebElement h2Element=driver.findElement(By.xpath("//div[@class=\"col-xs-12 text-center\"]/h2"));
	
	Assert.assertEquals(h2Element.getText(), "Appointment Confirmation");
	driver.quit();
	}
	

}
