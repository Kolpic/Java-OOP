package ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        products = new ArrayList<>();
    }

    public void buyProduct(Product product) {
        if (product.getCost() <= money) {
            money -= product.getCost();
            products.add(product);
            System.out.printf("%s bought %s%n",getName(),product);
        } else {
            throw new IllegalStateException(name + " can't afford " + product);
        }
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalStateException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ");

        if (!products.isEmpty()) {

            for (Product product : products) {
                sb.append(product);
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 2);


        } else {
            sb.append("Nothing bought");

        }
        return sb.toString();
    }
}
