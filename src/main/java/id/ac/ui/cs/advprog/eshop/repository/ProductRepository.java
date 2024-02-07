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

    public Product create(Product product){
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product editedProduct){
        for(int i=0; i<productData.size(); i++){
            if(productData.get(i).getProductId().equals(editedProduct.getProductId())){
                productData.get(i).setProductName(editedProduct.getProductName());
                productData.get(i).setProductQuantity(editedProduct.getProductQuantity());
                return productData.get(i);
            }
        }
        return null;
    }

    public Product findByProductId(String productId){
        for(Product product : productData){
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        return null;
    }

    public void delete(String productId){
        productData.remove(findByProductId(productId));
    }
}