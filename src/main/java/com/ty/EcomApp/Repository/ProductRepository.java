package com.ty.EcomApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ty.EcomApp.Model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
