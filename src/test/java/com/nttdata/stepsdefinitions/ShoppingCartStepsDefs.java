    package com.nttdata.stepsdefinitions;

    import com.nttdata.page.CartPage;
    import com.nttdata.steps.ShoppingCartSteps;
    import io.cucumber.java.After;
    import io.cucumber.java.Before;
    import io.cucumber.java.Scenario;
    import io.cucumber.java.es.Dado;
    import io.cucumber.java.es.Cuando;
    import io.cucumber.java.es.Entonces;
    import io.cucumber.java.es.Y;
    import org.junit.jupiter.api.Assertions;
    import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;

    import io.cucumber.java.BeforeStep;
    import io.cucumber.java.Scenario;

    public class ShoppingCartStepsDefs {

        private WebDriver driver;
        private Scenario scenario;
        private ShoppingCartSteps shoppingCartSteps;

        @Before
        public void setUp(Scenario scenario) {
            this.scenario = scenario;
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            shoppingCartSteps = new ShoppingCartSteps(driver);
        }

        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

        @Dado("que estoy en la página de la tienda")
        public void queEstoyEnLaPáginaDeLaTienda() {
            shoppingCartSteps.navegarATienda();
            screenShot();
        }

        @Y("me logueo con mi usuario {string} y clave {string}")
        public void meLogueoConMiUsuarioYClave(String usuario, String clave) {
            shoppingCartSteps.iniciarSesion(usuario, clave);
            screenShot();
        }

        @Cuando("navego a la categoria {string} y subcategoria {string}")
        public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
            shoppingCartSteps.navegarACategoria(categoria, subcategoria);
            screenShot();
        }

        @Y("agrego {int} unidades del primer producto al carrito")
        public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
            shoppingCartSteps.agregarProductoAlCarrito(cantidad);
            screenShot();
        }

        @Entonces("valido en el popup la confirmación del producto agregado")
        public void validoEnElPopupLaConfirmacionDelProductoAgregado() {
            String confirmacion = shoppingCartSteps.obtenerConfirmacionProductoAgregado();
            Assertions.assertNotNull(confirmacion, "Producto no agregado correctamente");
            screenShot();
        }

        @Entonces("valido en el popup que el monto total sea calculado correctamente")
        public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
            double montoTotal = shoppingCartSteps.obtenerMontoTotalProductoAgregado();
            double precioUnitario = 19.12;
            int cantidad = 2;
            double montoEsperado = precioUnitario * cantidad;
            Assertions.assertEquals(montoEsperado, montoTotal, "El monto total no se calculó correctamente");
            screenShot();
        }
        @Cuando("finalizo la compra")
        public void finalizoLaCompra() {
            shoppingCartSteps.finalizarCompra();
            screenShot();
        }
        @Entonces("valido el titulo de la pagina del carrito")
        public void valido_el_titulo_de_la_pagina_del_carrito() {
            String tituloActual = shoppingCartSteps.obtenerTituloPaginaCarrito();
            Assertions.assertTrue(tituloActual.contains("CARRITO"), "El título de la página del carrito no contiene la palabra 'Carrito'");
            screenShot();
        }

        @Entonces("vuelvo a validar el calculo de precios en el carrito")
        public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
            CartPage cartPage = new CartPage(driver); // Inicializa cartPage
            double montoTotalCarrito = cartPage.obtenerMontoTotalCarrito();
            double precioUnitario = 19.12;
            int cantidad = 2;
            double montoEsperado = precioUnitario * cantidad;
            Assertions.assertEquals(montoEsperado, montoTotalCarrito, "El monto total en el carrito no se calculó correctamente");
            screenShot();
        }

        private void screenShot() {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }
