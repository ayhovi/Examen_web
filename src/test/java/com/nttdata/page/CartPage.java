package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores
    private static final By MONTO_TOTAL_CARRITO = By.xpath("//div[@class='cart-summary-line cart-total']//span[@class='value']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public double obtenerMontoTotalCarrito() {
        try {
            String montoText = wait.until(ExpectedConditions.visibilityOfElementLocated(MONTO_TOTAL_CARRITO)).getText().replace("S/", "").trim();
            return Double.parseDouble(montoText);
        } catch (Exception e) {
            System.out.println("Error al obtener el monto total del carrito: " + e.getMessage());
            return 0.0;
        }
    }

    public String obtenerTituloPaginaCarrito() {
        try {
            return driver.findElement(By.xpath("//h1[@class='h1']")).getText();
        } catch (Exception e) {
            System.out.println("Error al obtener el título de la página del carrito: " + e.getMessage());
            return "";
        }
    }
}

