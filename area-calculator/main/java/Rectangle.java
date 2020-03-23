public class Rectangle implements Shape{
    private double width;
    private double heigth;

    public Rectangle(double width, double heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    @Override
    public double getArea() throws NoAreaException {
        if(heigth<=0 || width <=0) throw new NoAreaException("Bad length");
        return width*heigth;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle");
    }


}
