import java.util.List;

public class City implements SumProvider{
    List<House> houses;

    public City(List<House> houses) {
        this.houses = houses;
    }

    @Override
    public double sum(){
        double soma=0;
        for(House house: houses){
            soma += house.getArea();
        }
        return soma;
    }
}
