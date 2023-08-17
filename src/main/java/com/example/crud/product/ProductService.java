package com.example.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.Optional;

@Service
public class ProductService {
    HashMap<String, Object> datos; // Objeto global
    private final IProductRepository IProductRepository;
    @Autowired
    public ProductService(IProductRepository IProductRepository){ // Constructor
        this.IProductRepository = IProductRepository;
    }
    public List<Product> getProducts() {
        return this.IProductRepository.findAll();

    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = IProductRepository.findByName(product.getName());
         datos = new HashMap<>();


        if(res.isPresent() && product.getId()==null){
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT

            );
        }
        datos.put("message", "Se guardadó con éxito");
        if(product.getId()!=null){
            datos.put("message", "Se actulizó con éxito");
        }
        IProductRepository.save(product);
        datos.put("data", product);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteProduct(Long id){
        datos = new HashMap<>();
        boolean existe=this.IProductRepository.existsById(id);
        if(!existe){
            datos.put("error", true);
            datos.put("message", "No existe un producto con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        IProductRepository.deleteById(id);
        datos.put("message", "Producto eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
