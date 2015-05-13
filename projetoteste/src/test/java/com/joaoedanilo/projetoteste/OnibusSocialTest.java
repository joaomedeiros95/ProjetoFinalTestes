package com.joaoedanilo.projetoteste;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidLauncher;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnibusSocialTest {
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
	static WebDriverWait wait;
	
	@BeforeClass
	public static void startSelendroidServer() throws Exception {
		if (selendroidServer != null) {
			selendroidServer.stopSelendroid();
		}
		
		SelendroidCapabilities caps = new SelendroidCapabilities("com.joaoemedeiros.onibussocial");
		
		driver = new SelendroidDriver(caps);
		wait = new WebDriverWait(driver, 30);
	}
	
	@AfterClass
	public static void stopSelendroidServer() {
		if (driver != null) {
			driver.quit();
		}
		if (selendroidServer != null) {
			selendroidServer.stopSelendroid();
		}
	}

	/** Verifica se o Spinner tem ônibus */
	@Test
	public void test1TemOnibusParaSelecionar() {
		WebElement spinner = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("Spinner")));
		spinner.click();
		
		WebElement elementosSpinner = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("44/Santa Maria")));
		driver.navigate().back();
		Assert.assertNotEquals(null, elementosSpinner);
	}
	
	@Test
	public void test2MudarAba() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//TextView[@value='SER ACOMPANHADO']"))).click();
		WebElement texto = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("section_label")));
		
		Assert.assertEquals("A partir do momento que você apertar o botão de iniciar compartilharemos a posição do ônibus "
				+ "que você está atualmente com qualquer pessoa que esteja usando esse APP. Nenhuma informação sua será divulgada. "
				+ "Não esqueça de manter o GPS e a conexão com a internet habilitados pois são essenciais para que o aplicativo funcione.", texto.getText());
	}
	
	@Test
	public void test3MenuSobre() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ActionMenuView"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//RelativeLayout)[1]"))).click();
		WebElement imagem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ImageView[@id='imageView1']")));
		
		Assert.assertNotEquals(null, imagem);
	}
	
	@Test 
	public void test4MenuPedidos() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ActionMenuView"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//RelativeLayout)[2]"))).click();
		WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//TextView[@id='textView2']")));
		
		Assert.assertNotEquals(null, text);
	}
	
}