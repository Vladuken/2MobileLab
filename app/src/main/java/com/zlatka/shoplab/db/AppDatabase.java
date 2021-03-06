package com.zlatka.shoplab.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zlatka.shoplab.db.dao.ProductDao;
import com.zlatka.shoplab.db.dao.ProductInBasketDao;
import com.zlatka.shoplab.db.model.Product;
import com.zlatka.shoplab.db.model.ProductInBasket;


@Database(entities = {Product.class, ProductInBasket.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract ProductInBasketDao productInBasketDao();


}
