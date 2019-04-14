package com.zlatka.shoplab.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductInBasketDao {

    @Query("SELECT * FROM productinbasket WHERE productId=:id")
    ProductInBasket getById(int id);

    @Query("SELECT * FROM productinbasket")
    List<ProductInBasket> getAll();

    @Query("SELECT * FROM product JOIN productinbasket ON productId == id")
    List<Product> getAllProducts();

    @Update
    void update(ProductInBasket productInBasket);

    @Insert
    void insertAll(ProductInBasket... productInBaskets);

    @Delete
    void delete(ProductInBasket productInBasket);
}
