package xyz.funnyboy.springsession.springsessiona.handler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestHandler
{
    @RequestMapping("/test/spring/session/set")
    public String testSession(HttpSession session) {
        final String value = "hello world";
        session.setAttribute("test-spring-session", value);
        return "Session域set值：" + value;
    }
}
