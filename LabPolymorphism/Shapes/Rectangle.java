package Shapes;

public class Rectangle extends Shape{

    private Double height;

    private Double width;

    public Rectangle(Double height, Double width) {
        this.setHeight(height);
        this.setWidth(width);
        this.calculateArea();
        this.calculatePerimeter();
    }

    private void setWidth(Double width) {
        this.width = width;
    }

    private void setHeight(Double height) {
        this.height = height;
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(this.height * 2 + this.width * 2);
    }

    @Override
    protected void calculateArea() {
        setArea(this.height * this.width);
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }
}
