package models;

import models.Item;

import java.util.Comparator;

public class PriceCompartor implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getPrice() > o2.getPrice()?1:o1.getPrice() == o2.getPrice()? 0:-1;
    }
}
