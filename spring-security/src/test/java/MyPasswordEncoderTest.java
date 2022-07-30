import com.vectorx.springsecurity.util.MyPasswordEncoder;

/**
 * @Description: MyPasswordEncoderTest
 * @Author: VectorX
 * @Date: 2022/7/30 18:52
 * @Version: V1.0
 */
public class MyPasswordEncoderTest
{
    public static void main(String[] args) {
        MyPasswordEncoder encoder = new MyPasswordEncoder();
        String s = encoder.encode("1234");
        System.out.println(s); // 81DC9BDB52D04DC20036DBD8313ED055
        boolean matches = encoder.matches("1234", s);
        System.out.println(matches);
        boolean matches2 = encoder.matches("1234", "3297F44B13955235245B2497399D7A93");
        System.out.println(matches2);
    }
}
