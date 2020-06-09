package am.vtc.chat.model;

public class Shipper_User {
    private int id;
    private Shipper shipper;
    private User user;
    private boolean wasShipped;

    public boolean isWasShipped() {
        return wasShipped;
    }

    public void setWasShipped(boolean wasShipped) {
        this.wasShipped = wasShipped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Shipper_User{" +
                "id=" + id +
                ", shipper=" + shipper +
                ", user=" + user +
                ", wasShipped=" + wasShipped +
                '}';
    }
}
