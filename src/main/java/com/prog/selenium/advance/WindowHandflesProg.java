package com.prog.selenium.advance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandflesProg {
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://orangehrm.qedgetech.com/symfony/web/index.php/auth/login");
		String parentWin = driver.getWindowHandle();
		driver.findElement(By.xpath("//img[@alt='LinkedIn OrangeHRM group']")).click();
		driver.findElement(By.xpath("//img[@alt='OrangeHRM on Facebook']")).click();
		driver.findElement(By.xpath("//img[@alt='OrangeHRM on twitter']")).click();
		driver.findElement(By.xpath("//img[@alt='OrangeHRM on youtube']")).click();

		Set<String> handles = driver.getWindowHandles();

		List<String> hlist = new ArrayList<String>(handles);

		if (switchToRightWindow("Facebook", hlist)) {
			System.out.println(driver.getCurrentUrl());
		}
		closeAllWind(hlist, parentWin);
		switchToParentWin(parentWin);
		

	}

	public static boolean switchToRightWindow(String windowTitle, List<String> hlist) {
		for (String e : hlist) {
			String title = driver.switchTo().window(e).getTitle();
			if (title.contains(windowTitle)) {
				System.out.println("FIND");
				return true;
			}
		}
		return false;
	}

	public static void switchToParentWin(String parentWin) {
		driver.switchTo().window(parentWin);
	}

	public static void closeAllWind(List<String> lList, String parentWin) {

		for (String e : lList) {
			if (!e.equals(parentWin)) {
				driver.switchTo().window(e).close();

			}
		}

	}

}
