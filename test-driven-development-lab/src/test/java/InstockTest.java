import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class InstockTest {

    private ProductStock instock;
    private Product product;

    @Before
    public void prepare() {
        this.instock = new Instock(new ArrayList<>());
        this.product = new Product("Lukanka", 5, 2);
    }

    @Test
    public void testAddInStockShouldContainThatProduct() {
        instock.add(product);
        Assert.assertTrue(instock.contains(product));
    }

    @Test
    public void testContainsShouldReturnFalseWhenProductIsMissing() {
        Assert.assertFalse(instock.contains(product));
    }

    @Test
    public void testGetCountShouldReturnCorectCount() {
        Assert.assertEquals(0,instock.getCount());
        instock.add(product);
        Assert.assertEquals(1,instock.getCount());
    }

    @Test
    public void testFindShouldReturnTheRightProduct() {
        List<Product> products = addMultipleProducts();
        int productIndex = 3;

        Product expected = products.get(productIndex);
        Product actual = instock.find(productIndex);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getLabel(), actual.getLabel());


//        instock.add(product);
//        Assert.assertEquals(product,instock.find(0));
//        Product product1 = new Product("Kebab", 4, 2);
//        instock.add(product1);
//        Assert.assertEquals(product1,instock.find(1));
    }

    private List<Product> addMultipleProducts() {
        List<Product> products = List.of(
                new Product("Product 2", 5, 1),
                new Product("Product 1", 7, 8),
                new Product("Product 4", 7, 8),
                new Product("Product 5", 1, 3),
                new Product("Product 3", 2, 1),
                new Product("Product 6", 4, 4)
        );
        products.forEach(instock::add);
        return products;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowIfThereIsNoSuchIndex() {
        instock.find(instock.getCount());
    }

    @Test
    public void testChangeQuantityShouldChangeCurrentQuantity() {
        instock.add(product);
        int expectedQuantity = product.getQuantity() + 10;
        instock.changeQuantity(product.getLabel(), expectedQuantity);
        Assert.assertEquals(expectedQuantity,product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldFailIfProductWithLabelIsMissing() {
        instock.changeQuantity("Missing", 10);
    }

    @Test
    public void testFindByLabelShouldReturnTheProductWithTheSameLabel() {
        addMultipleProducts();
        instock.add(product);

        Product actual = instock.findByLabel(product.getLabel());

        Assert.assertNotNull(actual);
        Assert.assertEquals(product.getLabel(), actual.getLabel());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldFailWhenProductWithLabelIsMissing() {
        instock.findByLabel("missing label");
    }

    @Test
    public void testFindByAlphabeticalOrderShouldShouldReturnCorrectNumberOfProducts() {
        addMultipleProducts();

        int expectedCount = 3;
        List<Product> actual = iterableToList(instock.findFirstByAlphabeticalOrder(expectedCount));
        Assert.assertEquals(expectedCount, actual.size());
    }

    @Test
    public void testFindByAlphabeticalOrderShouldReturnTheProductsOrderedByLabel() {
        List<Product> products = addMultipleProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .toList();
        int expectedCount = products.size();

        List<Product> actual = iterableToList(instock.findFirstByAlphabeticalOrder(expectedCount));

        Assert.assertEquals(expectedCount, actual.size());

        for (int i = 0; i < products.size(); i++) {
            String expectedLabel = products.get(i).getLabel();
            String actualLabel = actual.get(i).getLabel();

            Assert.assertEquals(expectedLabel, actualLabel);
        }
    }

    @Test
    public void testFindByAlphabeticalOrderShouldReturnEmptyCollectionWhenNotEnoughProducts() {
        int size = addMultipleProducts().size();

        List<Product> products =iterableToList(instock.findFirstByAlphabeticalOrder(size + 1));
        Assert.assertEquals(0,products.size());

    }

    private List<Product> iterableToList(Iterable<Product> iterable) {
        Assert.assertNotNull(iterable);
        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);
        return products;
    }

    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRange() {

        final int beginRange = 2;
        final int endRange = 13;

        List<Product> expected = addMultipleProducts()
                .stream()
                .filter(p -> p.getPrice() > beginRange && p.getPrice() <= endRange)
                .toList();

        List<Product> actual = iterableToList(instock.findAllInRange(beginRange, endRange));

        Assert.assertEquals(expected.size(), actual.size());

        boolean hasNoOutOfRangePrices = actual.stream()
                .map(Product::getPrice)
                .noneMatch(p -> p <= beginRange || p > endRange);

        Assert.assertTrue(hasNoOutOfRangePrices);
    }

    @Test
    public void testFindAllInRangeShouldReturnOrderedProductsByPriceDescending() {

        final int beginRange = 2;
        final int endRange = 13;

        List<Product> expected = addMultipleProducts()
                .stream()
                .filter(p -> p.getPrice() > beginRange && p.getPrice() <= endRange)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .toList();

        List<Product> actual = iterableToList(instock.findAllInRange(beginRange, endRange));

        Assert.assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            double expectedPrice = expected.get(i).getPrice();
            double actualPrice = actual.get(i).getPrice();

            Assert.assertEquals(expectedPrice, actualPrice,0.00);
        }
    }

    @Test
    public void testFindAllByPriceShouldReturnMatchingPriceProducts() {
        addMultipleProducts();

        double expectedPrice = 7;
        
        List<Product> products = iterableToList(instock.findAllByPrice(expectedPrice));

        for (Product p : products) {
            Assert.assertEquals(expectedPrice, p.getPrice(),0.00);
        }
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionWhenThereIsNoMatches() {
        addMultipleProducts();

        double expectedPrice = -3;

        List<Product> products = iterableToList(instock.findAllByPrice(expectedPrice));

        Assert.assertEquals(0, products.size());
    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnTheCorrectMostExpensiveProducts() {
        int productsToTake = 4;

        List<Product> expectedProducts = addMultipleProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(productsToTake)
                .toList();

        List<Product> actualProducts = iterableToList(instock.findFirstMostExpensiveProducts(productsToTake));

        Assert.assertEquals(expectedProducts.size(), actualProducts.size());

        for (int i = 0; i < expectedProducts.size(); i++) {
            double expectedPrice = expectedProducts.get(i).getPrice();
            double actualPrice = actualProducts.get(i).getPrice();

            Assert.assertEquals(expectedPrice, actualPrice,0.00);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsShouldThrowIfLessThanCountsExists() {

        int size = addMultipleProducts().size();

        instock.findFirstMostExpensiveProducts(size + 1);
    }

    @Test
    public void testFindAllByQuantityShouldReturnMatchingProducts() {
        addMultipleProducts();

        int expectedQuantity= 8;

        List<Product> products = iterableToList(instock.findAllByQuantity(expectedQuantity));

        for (Product p : products) {
            Assert.assertEquals(expectedQuantity, p.getQuantity());
        }
    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollectionWhenThereIsNoMatches() {
        addMultipleProducts();

        int expectedPrice = -3;

        List<Product> products = iterableToList(instock.findAllByQuantity(expectedPrice));

        Assert.assertEquals(0, products.size());
    }

    @Test
    public void testIteratorShouldReturnAllTheProductsInStock() {

        List<Product> expected = addMultipleProducts();

        Iterator<Product> iterator = instock.iterator();

        List<Product> actual = new ArrayList<>();

        iterator.forEachRemaining(actual::add);

        Assert.assertEquals(expected,actual);
    }

}