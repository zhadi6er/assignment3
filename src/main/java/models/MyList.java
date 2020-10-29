package models;

import models.FromDB;
import models.Item;

import java.util.HashSet;
import java.util.Set;

public class MyList<N extends Item> implements FromDB {
    private int id;
    private Set<N> set;

    public MyList() {
        set = new HashSet<N>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<N> getSet() {
        return set;
    }

    public void setSet(Set<N> set) {
        this.set = set;
    }

    public void add(N item){
        set.add(item);
    }
}
