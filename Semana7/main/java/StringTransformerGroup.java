import java.util.List;

public class StringTransformerGroup implements StringTransformer {
    List<StringTransformer> transformations;

    public StringTransformerGroup(List<StringTransformer> transformations) {
        this.transformations = transformations;
    }

    @Override
    public void execute(StringDrink drink){
        for (StringTransformer st : transformations){
            st.execute(drink);
        }
    }


}
