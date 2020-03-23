import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTester {


    @Test
    public void Test1(){
        Rectangle r1 = new Rectangle(5, 10);

        try {
            assertEquals(50, r1.getArea(),0.1);
        }catch (NoAreaException a){
            fail("Throw an exeption when it shouldnt had");
        }

        Rectangle r2 = new Rectangle(0, 10);

        try{
            r2.getArea();
            fail("Didn't catch the execpetion");
        }catch (NoAreaException a){

        }

    }
}
