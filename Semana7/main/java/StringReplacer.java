public class StringReplacer implements StringTransformer{
    private char toReplace, replacer;

    public StringReplacer(char toReplace, char replacer) {
        this.toReplace = toReplace;
        this.replacer = replacer;
    }

    @Override
    public void execute(StringDrink drink) {
        String res = drink.getText().replace(toReplace,replacer);
        drink.setText(res);
    }
}
