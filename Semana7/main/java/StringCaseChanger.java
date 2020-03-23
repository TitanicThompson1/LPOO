import static java.lang.Character.*;

public class StringCaseChanger implements StringTransformer {

    @Override
    public void execute(StringDrink drink) {
        String s = drink.getText();
        char[] res = new char[s.length()];
        for( int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(isLowerCase(c)){
                c = toUpperCase(c);
            }else {
                c = toLowerCase(c);
            }
            res[i] = c;
        }
        drink.setText(new String(res));

    }
}
