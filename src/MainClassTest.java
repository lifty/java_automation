import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test public void testGetLocalNumber() {
        int test_n = getLocalNumber();
        boolean n_check = true;
        if (test_n == 14) {
            System.out.println("testGetLocalNumber passed - the method returns " + test_n);
        }
        else {
            n_check = false;
        }
        assert n_check: "An error occured: the number is not 14, it's " + test_n;
    }

    @Test public void testGetClassNumber() {
        int test_cn = getClassNumber();
        boolean cn_check = true;
        if (test_cn > 45) {
            System.out.println("testGetClassNumber passed, " + test_cn + "> 45");
        } else {
            cn_check = false;
        }
        assert cn_check: "An error occured: the returned number " + test_cn + " <= 45";
    }

    @Test public void testGetClassString() {
        String test_cs = getClassString();
        String str_part = "hello";
        boolean str_check = test_cs.toLowerCase().contains(str_part.toLowerCase());
        if (str_check == true) {
            System.out.println("testGetClassString passed - string includes " + str_part);
        }
        assert str_check: "An error occured: the string '" + test_cs + "' does not include '" + str_part + "'";
    }
}
