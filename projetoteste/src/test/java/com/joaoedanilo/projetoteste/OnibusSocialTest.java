package com.joaoedanilo.projetoteste;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidLauncher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnibusSocialTest {
	private static SelendroidLauncher selendroidServer = null;
	private static WebDriver driver = null;
	
	@BeforeClass
	public static void startSelendroidServer() throws Exception {
		if (selendroidServer != null) {
			selendroidServer.stopSelendroid();
		}
//		SelendroidConfiguration config = new SelendroidConfiguration();
//		config.addSupportedApp("src/resources/OnibusSocial.apk");
//		selendroidServer = new SelendroidLauncher(config);
//		selendroidServer.launchSelendroid();
		
		SelendroidCapabilities caps = new SelendroidCapabilities("com.joaoemedeiros.onibussocial");
		
		driver = new SelendroidDriver(caps);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
		WebElement spinner = driver.findElement(By.tagName("Spinner"));
		spinner.click();
		
		List<WebElement> elementosSpinner = driver.findElements(By.linkText("44/Santa Maria"));
		Assert.assertNotEquals(0, elementosSpinner.size());
	}
	
	@Test
	public void test2MudarAba() {
		driver.findElement(By.xpath("(//TabView)[1]")).click();
		WebElement texto = driver.findElement(By.id("section_label"));
		
		Assert.assertEquals("A partir do momento que você apertar o botão de iniciar compartilharemos a posição do ônibus "
				+ "que você está atualmente com qualquer pessoa que esteja usando esse APP. Nenhuma informação sua será divulgada. "
				+ "Não esqueça de manter o GPS e a conexão com a internet habilitados pois são essenciais para que o aplicativo funcione.", texto.getText());
	}


}