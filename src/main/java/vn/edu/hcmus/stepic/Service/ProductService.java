package vn.edu.hcmus.stepic.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.stepic.Base.ResponseBody;
import vn.edu.hcmus.stepic.Domain.ProductEntity;
import vn.edu.hcmus.stepic.Repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }
    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    }

    public ProductEntity findByProductId(Long id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product is not exist!"));
        return product;
    }
    public ResponseEntity<?> createProduct(ProductEntity product){
        return ResponseEntity.ok().body(new ResponseBody("Create success!", productRepository.save(product)));
    }
    public ResponseEntity<?> updateProduct(long id, ProductEntity product) {
        ProductEntity updateProduct = findByProductId(id);

        updateProduct.setName(product.getName());
        updateProduct.setBrand(product.getBrand());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDiscount(product.getDiscount());
        updateProduct.setLogoImage(product.getLogoImage());
        updateProduct.setGameImage(product.getGameImage());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setShortDesc(product.getShortDesc());
        updateProduct.setPublishDate(product.getPublishDate());
        updateProduct.setPurchaseAmount(product.getPurchaseAmount());
        updateProduct.setRating(product.getRating());

        return ResponseEntity.ok().body(new ResponseBody("Update success!", productRepository.save(updateProduct)));
    }
    public ResponseEntity<?> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
    public ResponseEntity<?> searchProduct(String keyword) {
        ResponseBody responseBody = new ResponseBody(productRepository.searchProduct(keyword));
        return ResponseEntity.ok().body(responseBody);
    }
    public ResponseEntity<?> buyProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not present"));
        userService.addProduct(product);
        return ResponseEntity.ok().body(new ResponseBody("Product bought successfully"));
    }
}

