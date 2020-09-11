package ru.vlasoff.fullstackcrud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlasoff.fullstackcrud.model.Product;
import ru.vlasoff.fullstackcrud.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductAPI {
    private final ProductService productService;

    @Autowired
    public ProductAPI(ProductService productService) {
        this.productService = productService;
    }

    // находим всех
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    // добавляем
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }
    // находим по id
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Optional<Product> optional = productService.findById(id);
        if (optional.isEmpty()){
            log.error("Product " + id + " doesn't exists!");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(optional.get());
    }
    // обновляем
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,
                                          @Valid @RequestBody Product product){
        if (productService.findById(id).isEmpty()){
            log.error("Product " + id + " doesn't exists!");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.save(product));
    }
    // удаляем
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if (productService.findById(id).isEmpty()){
            log.error("Product " + id + " doesn't exists!");
            ResponseEntity.badRequest().build();
        }

        productService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
