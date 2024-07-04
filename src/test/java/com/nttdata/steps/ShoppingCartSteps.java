package com.nttdata.steps;

import com.nttdata.page.StorePage;
import com.nttdata.page.CartPage;
import org.openqa.selenium.WebDriver;

public class ShoppingCartSteps {

    private WebDriver driver;
    private StorePage storePage;
    private CartPage cartPage;

    public ShoppingCartSteps(WebDriver driver) {
        this.driver = driver;
        this.storePage = new StorePage(driver);
        this.cartPage = new CartPage(driver);
    }

    public void navegarATienda() {
        driver.get("https://qalab.bensg.com/store");
    }

    public void iniciarSesion(String usuario, String clave) {
        storePage.iniciarSesion();
        storePage.ingresarUsuario(usuario);
        storePage.ingresarClave(clave);
        storePage.clickBotonLogin();
    }

    public void navegarACategoria(String categoria, String subcategoria) {
        storePage.seleccionarCategoria(categoria);
        storePage.seleccionarSubcategoria(subcategoria);
    }

    public void agregarProductoAlCarrito(int cantidad) {
        storePage.seleccionarPrimerProducto();
        storePage.ingresarCantidad(cantidad);
        storePage.clickBotonAgregarAlCarrito();
    }

    public String obtenerConfirmacionProductoAgregado() {
        return storePage.obtenerConfirmacionProductoAgregado();
    }

    public int obtenerCantidadProductoAgregado() {
        return storePage.obtenerCantidadProductoAgregado();
    }

    public double obtenerMontoTotalProductoAgregado() {
        return storePage.obtenerMontoTotalProductoAgregado();
    }

    public void finalizarCompra() {
        storePage.clickBotonFinalizarCompra();
    }

    public String obtenerTituloPaginaCarrito() {
        return cartPage.obtenerTituloPaginaCarrito();
    }
}
