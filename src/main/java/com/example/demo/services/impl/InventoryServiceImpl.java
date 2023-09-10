package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Film;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Store;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.FilmRepo;
import com.example.demo.repo.InventoryRepo;
import com.example.demo.repo.StoreRepo;
import com.example.demo.services.IInventoryService;

@Service
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private FilmRepo filmRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Inventory> findAll() {
        return inventoryRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Inventory> findById(Integer id) {
        return inventoryRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return inventoryRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Inventory> insert(Inventory saveInventory) {
        Optional<Film> oFilm = filmRepo.findById(saveInventory.getFilm().getFilmId());
        Optional<Store> oStore = storeRepo.findById(saveInventory.getStore().getStoreId());
        if(oFilm.isPresent() && oStore.isPresent()) {
            Inventory inventory = new Inventory();
            inventory.setInventoryId(null);
            inventory.setFilm(oFilm.get());
            inventory.setStore(oStore.get());
            inventory.setLastUpdate(saveInventory.getLastUpdate());
            Inventory o = inventoryRepo.save(inventory);
            return inventoryRepo.findById(o.getInventoryId());
        }
        throw new ObjectNotFoundException("Film or Store are not exist.");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Inventory> update(Integer inventoryId, Inventory saveInventory) {
        Optional<Film> oFilm = filmRepo.findById(saveInventory.getFilm().getFilmId());
        Optional<Store> oStore = storeRepo.findById(saveInventory.getStore().getStoreId());
        saveInventory.setInventoryId(inventoryId);
        Optional<Inventory> inventoryOptional = inventoryRepo.findById(saveInventory.getInventoryId());
        if (inventoryOptional.isPresent() && oFilm.isPresent() && oStore.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            inventory.setFilm(oFilm.get());
            inventory.setStore(oStore.get());
            inventory.setLastUpdate(saveInventory.getLastUpdate());
            Inventory o = inventoryRepo.save(inventory);
            return inventoryRepo.findById(o.getInventoryId());
        }
        throw new ObjectNotFoundException("Film or Store are not exist.");
    }
}

