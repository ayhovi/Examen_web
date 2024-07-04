package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StorePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores
    private static final By INICIAR_SESION = By.xpath("//span[contains(text(),'Iniciar sesión')]");
    private static final By EMAIL_INPUT = By.xpath("//input[@id='field-email']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@id='field-password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@id='submit-login']");
    private static final By CATEGORIA_CLOTHES = By.xpath("//header/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/a[1]");
    private static final By SUBCATEGORIA_MEN = By.xpath("//body/main[1]/section[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/ul[1]/li[1]/a[1]");
    private static final By PRIMER_PRODUCTO = By.xpath("//body/main[1]/section[1]/div[1]/div[1]/div[2]/section[1]/section[1]/div[3]/div[1]/div[1]/article[1]/div[1]/div[1]/a[1]/picture[1]/img[1]");
    private static final By AUMENTAR_CANTIDAD = By.xpath("//body/main[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[2]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/span[3]/button[1]/i[1]");
    private static final By AGREGAR_CARRITO = By.xpath("//body/main[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/button[1]");
    private static final By MONTO_TOTAL = By.xpath("//body/div[@id='blockcart-modal']/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/p[4]/span[2]");
    private static final By FINALIZAR_COMPRA = By.xpath("//body/div[@id='blockcart-modal']/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]");
    private static final By PRODUCTO_AGREGADO_CONFIRMACION = By.xpath("//div[@class='modal-body']//h6[contains(@class, 'product-name')]");
    private static final By PRODUCTO_AGREGADO_CANTIDAD = By.xpath("//div[@class='modal-body']//span[@class='product-quantity']/strong");
    private static final By PRODUCTO_AGREGADO_MONTO_TOTAL = By.xpath("//div[@class='modal-body']//p[@class='product-total']//span[@class='value']");

    public StorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void iniciarSesion() {
        wait.until(ExpectedConditions.elementToBeClickable(INICIAR_SESION)).click();
    }

    public void ingresarUsuario(String usuario) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT)).sendKeys(usuario);
    }

    public void ingresarClave(String clave) {
        driver.findElement(PASSWORD_INPUT).sendKeys(clave);
    }

    public void clickBotonLogin() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void seleccionarCategoria(String categoria) {
        if (categoria.equalsIgnoreCase("Clothes")) {
            wait.until(ExpectedConditions.elementToBeClickable(CATEGORIA_CLOTHES)).click();
        }
    }

    public void seleccionarSubcategoria(String subcategoria) {
        if (subcategoria.equalsIgnoreCase("Men")) {
            wait.until(ExpectedConditions.elementToBeClickable(SUBCATEGORIA_MEN)).click();
        }
    }

    public void seleccionarPrimerProducto() {
        wait.until(ExpectedConditions.elementToBeClickable(PRIMER_PRODUCTO)).click();
    }

    public void ingresarCantidad(int cantidad) {
        for (int i = 1; i < cantidad; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(AUMENTAR_CANTIDAD)).click();
        }
    }

    public void clickBotonAgregarAlCarrito() {
        wait.until(ExpectedConditions.elementToBeClickable(AGREGAR_CARRITO)).click();
    }

    public String obtenerConfirmacionProductoAgregado() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTO_AGREGADO_CONFIRMACION)).getText();
    }

    public int obtenerCantidadProductoAgregado() {
        String cantidadText = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTO_AGREGADO_CANTIDAD)).getText();
        return Integer.parseInt(cantidadText);
    }

    public double obtenerMontoTotalProductoAgregado() {
        String montoText = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTO_AGREGADO_MONTO_TOTAL)).getText().replace("S/", "").trim();
        return Double.parseDouble(montoText);
    }

    public void clickBotonFinalizarCompra() {
        wait.until(ExpectedConditions.elementToBeClickable(FINALIZAR_COMPRA)).click();
    }
}

