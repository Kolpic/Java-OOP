import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;

    public Instock(List<Product> products) {
        this.products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public boolean contains(Product product) {
        return products.stream().anyMatch(p -> p.getLabel().equals(product.getLabel()));
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        Product product1 = findByLabel(product);
        product1.setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        return products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return products.stream()
                .filter(p -> p.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException
                        ("Unable to find product with label " + label));
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {

        if (count > products.size() || count <= 0) {
            return new ArrayList<>();
        }

        return products.stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .limit(count)
                .toList();
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return products.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .toList();
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return products.stream()
                .filter(e -> e.getPrice() == price)
                .toList();
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {

        if (count <= 0 || count > products.size()) {
            throw new IllegalArgumentException("Not enough products in stock we have "
                    + products.size() + " but required were " + count);
        }

        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return products.stream()
                .filter(e -> e.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
