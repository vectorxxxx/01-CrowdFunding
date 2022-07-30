import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: BCryptPasswordEncoderTest
 * @Author: VectorX
 * @Date: 2022/7/30 18:53
 * @Version: V1.0
 */
public class BCryptPasswordEncoderTest
{
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("1234");
        System.out.println(encode);
        // $2a$10$jcgeJifPCuv8XEL4aKsro.Bvm4SF12GjfQieWH3WaH4q2OyfeUyc2
        // $2a$10$uaZOO6ZY6Mj3SMoOYcwoCeQLJv.BIPNBd3QDCTKPfOcLCdnVvi8lu
        // $2a$10$toIUk/gpBIExIlbEF87Giu7XuY0PeNBzXoMhLBhzuRvvvolv0ikwe
    }
}
