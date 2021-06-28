package com.acarasiov.vending_machine.dao;

import com.acarasiov.vending_machine.model.Config;
import com.acarasiov.vending_machine.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VendingMachineDaoInMemImpl implements VendingMachineDao {

    Map<Integer, Item> items;

    public VendingMachineDaoInMemImpl() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.json").getFile());
        Config configuration = mapper.readValue(file, Config.class);
        items = new HashMap<>();
        for (int i = 0; i < configuration.getItems().size(); i++) {
            configuration.getItems().get(i).setItemId(i+1);
            items.put(configuration.getItems().get(i).getItemId(), configuration.getItems().get(i));
        }

    }

    @Override
    public void vendItem(Item item) {
        int inventoryChange = item.getAmount();
        inventoryChange--;
        item.setAmount(inventoryChange);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItemById(int itemId) {
        return items.get(itemId);
    }
}
