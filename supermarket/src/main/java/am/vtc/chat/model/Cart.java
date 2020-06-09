package am.vtc.chat.model;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    private int id;
    private List<Shoping> shopings;
    private long lastTimeToAdd;

    public Cart() {this.shopings = new LinkedList<>(); }

    public void setShoping(Shoping shoping) {
        this.shopings.add(shoping);
        this.lastTimeToAdd = System.currentTimeMillis();
    }

    public void setShoping(Shoping[] shoping) {
        this.shopings.addAll(shopings);
        this.lastTimeToAdd = System.currentTimeMillis();
    }

    public List<Shoping> getShoping() {
        return this.shopings;
    }

    public long getLastTimeToAdd() {
        return lastTimeToAdd;
    }

    public void setLastTimeToAdd(long lastTimeToAdd) {
        this.lastTimeToAdd = lastTimeToAdd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
