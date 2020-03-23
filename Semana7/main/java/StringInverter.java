public class StringInverter implements StringTransformer {

    @Override
    public void execute(StringDrink drink) {
        String temp = drink.getText();

        StringBuffer inv = new StringBuffer(temp);

        drink.setText(inv.reverse().toString());
    }
}
