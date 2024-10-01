import static org.testng.Assert.assertEquals;

import java.security.PublicKey;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.asm.Advice.Local;

public class ClassThree {

	String url = "https://global.almosafer.com/en";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void pub() {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']")).click();
	}

	@Test()
	public void checkLanguageandcurrency() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage, "en");

		String ActualCurrency = driver.findElement(By.xpath("//button[normalize-space()='SAR']")).getText();
		Assert.assertEquals(ActualCurrency, "SAR");
	}

	@Test()
	public void checkPhonenumber() {

		String ActualPhone = driver.findElement(By.xpath("//strong[normalize-space()='+966554400000']")).getText();
		Assert.assertEquals(ActualPhone, "+966554400000");
	}

	@Test()
	public void checkthelogo() {

		boolean qatifisDisplayed = driver.findElement(By.xpath("//div[@class='sc-fihHvN eYrDjb']")) != null;
		Assert.assertEquals(qatifisDisplayed, true);
	}

	@Test()
	public void checkhoteltab() {
		String Actualtab = driver.findElement(By.xpath("//a[@id='uncontrolled-tab-example-tab-hotels']"))
				.getAttribute("aria-selected");
		Assert.assertEquals(Actualtab, "false");
	}

	@Test(enabled = false)
	public void LanguageRandomly() {
		String[] websiteLanguage = { "https://www.almosafer.com/en?ncr=1", "https://www.almosafer.com/ar?ncr=1" };
		Random rand = new Random();
		int randomNumber = rand.nextInt(websiteLanguage.length);
		driver.get(websiteLanguage[randomNumber]);

		String myWebsiteUrl = driver.getCurrentUrl();
		if (myWebsiteUrl.contains("ar")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLanguage, "ar");
		} else if (myWebsiteUrl.contains("en")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLanguage, "en");
		}
	}

	@Test()
	public void date() {
		LocalDate today = LocalDate.now();
		int expectedDepatureDate = today.plusDays(1).getDayOfMonth();
		int expectedReturnDate = today.plusDays(2).getDayOfMonth();
		WebElement ActualDepture = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"));
		WebElement ActualReturn = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"));

		Assert.assertEquals(Integer.parseInt((ActualDepture).getText()), expectedDepatureDate);
		Assert.assertEquals(Integer.parseInt((ActualReturn).getText()), expectedReturnDate);
	}
	@Test()
	public void welcomeText() {
		
		String welcometext = "Let’s book your next trip!"; 
		String ActualText = driver.findElement(By.xpath("//h1[contains(text(),'Let’s book your next trip!')]")).getText();
		
		Assert.assertEquals(welcometext, ActualText);
	}
	
	@AfterTest
	public void pup() {

	}
}
