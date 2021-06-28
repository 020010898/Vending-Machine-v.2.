package com.acarasiov.vending_machine.dao;


import com.acarasiov.vending_machine.model.Item;

import java.util.List;

public interface VendingMachineDao {

    public void vendItem(Item item);

    public List<Item> getAllItems();

    public Item getItemById(int itemId);
}
