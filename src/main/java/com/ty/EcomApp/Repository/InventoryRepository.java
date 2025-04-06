package com.ty.EcomApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ty.EcomApp.Model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}
