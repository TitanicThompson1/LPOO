import java.util.ArrayList;
import java.util.List;

public class AreaAggregator implements SumProvider {
    private List<HasArea> areas = new ArrayList<>();

    public void addShape(HasArea area){
        this.areas.add(area);
    }

    @Override
    public double sum()  {
        double sum= 0;
        try {
            for (HasArea area : areas) {
                sum += area.getArea();
            }
        }catch (NoAreaException a){
            return -1;
        }
        return sum;
    }
}
