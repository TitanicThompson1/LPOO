import java.util.List;

public class StringRecipe {
    List<StringTransformer> transformations;

    public StringRecipe(List<StringTransformer> transformations) {
        this.transformations = transformations;
    }

    public void mix(StringDrink drink){
        for (StringTransformer st : transformations){
            st.execute(drink);
        }
    }
}
