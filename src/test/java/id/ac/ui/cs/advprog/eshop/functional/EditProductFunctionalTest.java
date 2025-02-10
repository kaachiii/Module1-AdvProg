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
public class EditProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;
    private String productId;

    @BeforeEach
    void setUpTest(ChromeDriver driver) {
        // Buka halaman pembuatan produk
        driver.get(String.format("%s:%d/product/create", testBaseUrl, serverPort));

        // Isi form pembuatan produk
        driver.findElement(By.name("productName")).sendKeys("Susu");
        driver.findElement(By.name("productQuantity")).sendKeys("10");
        driver.findElement(By.tagName("button")).click();

        // Ambil ID produk dari halaman daftar
        driver.get(String.format("%s:%d/product/list", testBaseUrl, serverPort));
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

        WebElement editButton = null;
        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.cssSelector("td:first-child")); // Kolom nama produk
            if (nameCell.getText().equals("Susu")) {
                editButton = row.findElement(By.cssSelector("a.btn.btn-primary.btn-sm"));
                break;
            }
        }
        String editUrl = editButton.getAttribute("href");

        // Ekstrak ID produk dari URL
        productId = editUrl.substring(editUrl.lastIndexOf("/") + 1);

        // Set base URL untuk edit produk
        baseUrl = String.format("%s:%d/product/edit/%s", testBaseUrl, serverPort, productId);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();
        assertEquals("Edit Product", welcomeMessage);
    }

    @Test
    void editProduct_isCorrect(ChromeDriver driver) throws Exception {
        // Buka halaman edit produk
        driver.get(baseUrl);

        // Edit nama dan jumlah produk
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));

        // Kosongkan input terlebih dahulu
        nameInput.clear();
        quantityInput.clear();

        // Masukkan nilai baru
        String newName = "Kacang Goreng";
        String newQuantity = "20";

        nameInput.sendKeys(newName);
        quantityInput.sendKeys(newQuantity);

        // Klik tombol submit
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Buka halaman daftar produk untuk verifikasi
        driver.get(String.format("%s:%d/product/list", testBaseUrl, serverPort));

        // Cari produk dengan nama baru dan pastikan nilainya benar
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

        boolean isUpdated = false;
        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.cssSelector("td:first-child"));
            WebElement quantityCell = row.findElement(By.cssSelector("td:nth-child(2)"));

            if (nameCell.getText().equals(newName) && quantityCell.getText().equals(newQuantity)) {
                isUpdated = true;
                break;
            }
        }

        // Pastikan perubahan berhasil
        assertEquals(true, isUpdated);
    }
}
