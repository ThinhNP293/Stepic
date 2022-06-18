package vn.edu.hcmus.stepic.Controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmus.stepic.Domain.ProductEntity;
import vn.edu.hcmus.stepic.Service.ProductService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    //ALL USER
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductEntity>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findByProductId(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.findByProductId(id));
    }

    //STORE
    @PreAuthorize("hasAnyAuthority('STORE')")
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ProductEntity product){
        return productService.createProduct(product);
    }

    @PreAuthorize("hasAnyAuthority('STORE')")
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody ProductEntity product){
        return productService.updateProduct(id, product);
    }

    @PreAuthorize("hasAnyAuthority('STORE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
