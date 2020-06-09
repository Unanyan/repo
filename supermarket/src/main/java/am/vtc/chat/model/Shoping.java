package am.vtc.chat.model;

public class Shoping {
    private int id;
    private Product product;
    private int count;
    private int weight;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shoping{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                ", weight=" + weight +
                '}';
    }
}
