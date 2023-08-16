package com.example.crud.product;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private float price;
    private LocalDate fecha;
    @Transient
    private int antiguedad;

    // Constructores
    public Product() {
    }

    // Constructor sobrecargado

    public Product(Long id, String name, float price, LocalDate fecha) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fecha = fecha;
    }

    // Cosntructor que no recibe el id

    public Product(String name, float price, LocalDate fecha) {
        this.name = name;
        this.price = price;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getAntuguedad() {
        return Period.between(this.fecha, LocalDate.now()).getYears(); // Campo calculado
    }

    public void setAntuguedad(int antuguedad) {
        this.antiguedad = antuguedad;
    }
}
