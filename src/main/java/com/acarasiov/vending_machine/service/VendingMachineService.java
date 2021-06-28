package com.acarasiov.vending_machine.service;


import com.acarasiov.vending_machine.exception.NoItemException;
import com.acarasiov.vending_machine.model.Change;
import com.acarasiov.vending_machine.model.Item;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {

    public void vendItem();

    public List<Item> getAllItems();

    public Item getItemById(int itemId) throws FileNotFoundException, NoItemException;

    public void addMoney(String amount);

    public BigDecimal getBalance();

    public void returnChange();

    String getTextMessage();

    Change getChange();

    int getItemChoice();

    void setBalance(BigDecimal balance);

    void setTextMessage(String textMessage);

    void setChange(Change change);

    void setItemChoice(int itemChoice);

}
