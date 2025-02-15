package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class ProductListFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default value to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();
        assertEquals("Product List", pageTitle);
    }

    @Test
    void messageProductList_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Product' List", welcomeMessage);
    }

    @Test
    void buttonCreateProduct_isCorrect(ChromeDriver driver) throws Exception {
        // Buka halaman daftar produk
        driver.get(baseUrl);

        // Temukan tombol "Create Product"
        WebElement createButton = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-sm.mb-3"));

        // Pastikan tombol memiliki teks yang benar
        assertEquals("Create Product", createButton.getText());

        // Klik tombol "Create Product"
        createButton.click();

        // Verifikasi bahwa halaman yang dibuka adalah halaman pembuatan produk
        String currentUrl = driver.getCurrentUrl();
        assertEquals(String.format("%s:%d/product/create", testBaseUrl, serverPort), currentUrl);

        // Pastikan judul halaman yang terbuka sesuai
        String pageTitle = driver.findElement(By.tagName("h3")).getText();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void deleteProduct_isCorrect(ChromeDriver driver) throws Exception {
        // Buka halaman pembuatan produk
        driver.get(String.format("%s:%d/product/create", testBaseUrl, serverPort));

        // Isi form pembuatan produk
        driver.findElement(By.name("productName")).sendKeys("Kecombrang");
        driver.findElement(By.name("productQuantity")).sendKeys("20");
        driver.findElement(By.tagName("button")).click();

        // Buka halaman daftar produk
        driver.get(baseUrl);

        // Cari produk dengan nama "Kecombrang" dan temukan tombol delete
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
        WebElement deleteButton = null;
        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.cssSelector("td:first-child"));
            if (nameCell.getText().equals("Kecombrang")) {
                deleteButton = row.findElement(By.cssSelector("a.btn.btn-danger.btn-sm"));
                break;
            }
        }

        // Klik tombol delete
        deleteButton.click();

        // Verifikasi produk "Kecombrang" sudah tidak ada di daftar produk
        driver.get(baseUrl); // Refresh halaman untuk memastikan perubahan
        boolean productStillExists = driver.findElements(By.xpath("//td[text()='Kecombrang']")).size() > 0;
        assertEquals(false, productStillExists);
    }
}
