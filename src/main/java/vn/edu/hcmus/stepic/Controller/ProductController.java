package vn.edu.hcmus.stepic.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmus.stepic.Domain.ProductEntity;
import vn.edu.hcmus.stepic.Service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    //ALL USER
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductEntity>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findByProductId(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.findByProductId(id));
    }

    //STORE
    //@PreAuthorize("hasAnyAuthority('STORE')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductEntity product){
        return productService.createProduct(product);
    }

    //@PreAuthorize("hasAnyAuthority('STORE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ProductEntity product){
        return productService.updateProduct(id, product);
    }

    //@PreAuthorize("hasAnyAuthority('STORE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByProductId(@RequestBody String keyword){
        return productService.searchProduct(keyword);
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Long id){
        return productService.buyProduct(id);
    }
}
