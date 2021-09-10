import org.junit.Test;

public class MainClass {
    public int n = 14;
    public int getLocalNumber() {
        return n;
    }

    private int class_number = 20;
    public int getClassNumber() {
        return class_number;
    }

    private String class_string = "Hello, world";
    public String getClassString(){
        return class_string;
    }
}
