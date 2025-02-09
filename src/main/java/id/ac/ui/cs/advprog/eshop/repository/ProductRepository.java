package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        product.setProductId(String.valueOf(productData.size()));
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit(Product product){
        for(Product currProduct : productData){

            int currProductID = Integer.parseInt(currProduct.getProductId());

            int productID = Integer.parseInt(product.getProductId());
            String productName = product.getProductName();
            int productQuantity = product.getProductQuantity();

            if(currProductID == productID){
                currProduct.setProductName(productName);
                currProduct.setProductQuantity(productQuantity);
                return currProduct;
            }
        }
        return null; // null case tidak mungkin terjadi karena ketika edit-product,
                    // product dijamin ada pada productData
    }
}
