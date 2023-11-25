package xyz.funnyboy.springsession.springsessionb.handler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestHandler
{
    @RequestMapping("/test/spring/session/get")
    public String testSession(HttpSession session) {
        final String value = String.valueOf(session.getAttribute("test-spring-session"));
        return "Session域get值：" + value;
    }
}
