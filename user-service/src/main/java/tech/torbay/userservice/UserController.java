package tech.torbay.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/users")
    public List<String> getUsers() {

        logger.info("Processing Users Request");

        List<String> users = Arrays.asList(
                new String("One"),
                new String("Two")
        );

        logger.info("Processed User Request");

        return users;
    }

}
