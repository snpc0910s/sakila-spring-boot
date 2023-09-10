package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Store;

public interface IStoreService {
    public List<Store> findAll();

    public Optional<Store> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Store> insert(Store saveStore);

    public Optional<Store> update(Integer storeId, Store saveStore);
}

