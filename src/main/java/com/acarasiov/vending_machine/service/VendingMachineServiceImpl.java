package com.acarasiov.vending_machine.service;




import com.acarasiov.vending_machine.dao.VendingMachineDao;
import com.acarasiov.vending_machine.exception.NoItemException;
import com.acarasiov.vending_machine.exception.NoItemIdException;
import com.acarasiov.vending_machine.exception.NoMoneyException;
import com.acarasiov.vending_machine.model.Change;
import com.acarasiov.vending_machine.model.Item;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    VendingMachineDao dao;
    private BigDecimal balance;
    private int itemChoice;
    private Change change;
    private String textMessage;

    public VendingMachineServiceImpl(VendingMachineDao dao){
        this.dao = dao;
        balance = new BigDecimal ("0.00");
        itemChoice = 0;
        change = null;
        textMessage = null;
    }

    private void validateItemInventory(Item item) throws NoItemException {
        if(item.getAmount() == 0){

            throw new NoItemException("SOLD OUT!!!");

        }
    }

    private void validateSufficientFunds(Item item, BigDecimal userMoneySelection) throws NoMoneyException {
        BigDecimal value = item.getItemPrice();
        if(value.compareTo(userMoneySelection) > 0 ){
            BigDecimal whatTheyOwe = value.subtract(balance);
            throw new NoMoneyException("Please deposit : $" + whatTheyOwe);

        }
    }

    private void validateId(Item item) throws NoItemIdException {
        if(item == null || itemChoice == 0 || item.getItemId() == 0){
            throw new NoItemIdException("No such item.");
        }
    }

    @Override
    public void vendItem(){
        try {
            Item item = dao.getItemById(itemChoice);
            validateId(item);
            BigDecimal cost = item.getItemPrice();
            validateItemInventory(item);
            validateSufficientFunds(item, balance);
            BigDecimal changeReturned = balance.subtract(cost);
            Change daChangeReturned = new Change(changeReturned);
            change = daChangeReturned;
            balance = new BigDecimal("0.00");
            dao.vendItem(item);
            textMessage = "Thank You!";
        } catch (NoItemException | NoMoneyException | NoItemIdException ex) {
            textMessage = ex.getMessage();
        }


    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItemById(int itemId) throws FileNotFoundException, NoItemException {
        Item item = dao.getItemById(itemId);
        validateItemInventory(item);
        return dao.getItemById(item.getItemId());
    }

    @Override
    public void addMoney(String amount) { //created a swtich statement to determine the new value of balance dependent upon which button the user is pressing
        switch (amount) {
            case "dollar": //these are the corresponding names to the buttons found in the jsp
                balance = balance.add(new BigDecimal("1.00"));
                break;
            case "quarter": //they use the URL Mapping to determine what the URL is depending on the name of the button
                balance = balance.add(new BigDecimal("0.25"));
                break;
            case "dime": //you can find these in the jsp
                balance = balance.add(new BigDecimal("0.10"));
                break;
            case "nickel": //addmoney/nickel
                balance = balance.add(new BigDecimal("0.05"));
                break;
            default:
        }
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public int getItemChoice() {
        return itemChoice;
    }

    @Override
    public void setItemChoice(int itemChoice) {
        this.itemChoice = itemChoice;
    }

    @Override
    public Change getChange() {
        return change;
    }

    @Override
    public void setChange(Change change) {
        this.change = change;
    }

    @Override
    public String getTextMessage() {
        return textMessage;
    }

    @Override
    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public void returnChange() { //resetting all values to nothing once change is returned
        Change theirChange = new Change(balance);
        change = theirChange;
        balance = new BigDecimal("0.00");
        itemChoice = 0;
        textMessage = null;

    }

}