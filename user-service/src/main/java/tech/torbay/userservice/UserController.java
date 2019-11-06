package tech.torbay.userservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @RequestMapping("/users")
    public List<String> getUsers() {
        List<String> users = Arrays.asList(
                new String("One"),
                new String("Two")
        );
        return users;
    }

}
