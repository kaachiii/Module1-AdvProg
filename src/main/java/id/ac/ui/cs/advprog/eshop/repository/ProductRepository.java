package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {

        // handle validasi input
        if("".equals(product.getProductName())){
            product.setProductName("Nama Produk Tidak Valid!");
        }

        productData.add(product);
        product.setProductId(UUID.randomUUID().toString());
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit(Product editProduct){
        String productID = editProduct.getProductId();
        Product product = findProductByID(productID);

        String productName = editProduct.getProductName();
        int productQuantity = editProduct.getProductQuantity();

        if("".equals(productName)){
            productName = "Nama Produk Tidak Valid!";
        }

        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        return product;
    }

    public Product findProductByID(String productId) {
        for(Product currentProduct : productData){
            String currentProductID = currentProduct.getProductId();
            if(currentProductID.equals(productId)){
                return currentProduct;
            }
        }
        return null;
    }

    public void delete(Product deleteProduct){
        productData.remove(deleteProduct);
    }

    public void deleteProductById(String deleteId){
        delete(findProductByID(deleteId));
    }
}
