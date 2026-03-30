package ma.ens.security.web;

import ma.ens.security.entities.User;
import ma.ens.security.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello USER";
    }

    @GetMapping("/user/profile")
    public Map<String, Object> profile(org.springframework.security.core.Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        return Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "active", user.isActive(),
                "roles", user.getRoles().stream().map(r -> r.getName()).toList()
        );
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello ADMIN";
    }
}

