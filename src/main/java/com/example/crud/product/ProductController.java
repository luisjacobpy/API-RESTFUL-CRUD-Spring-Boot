package com.example.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/products") // Version
public class ProductController {

    private final ProductService productService;

    @Autowired // Anotación para inyectar esta clase en el constructor
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registrarProducto(@RequestBody Product product) {
        return this.productService.newProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarProducto(@RequestBody Product product) {
        return this.productService.newProduct(product);
    }


    @DeleteMapping(path = {"productId"})
    public ResponseEntity<Object> eliminar(@PathVariable("productId") Long id) {
        return this.productService.deleteProduct(id);
    }
}
