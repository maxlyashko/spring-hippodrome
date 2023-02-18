package ua.lyashko.module3.service;

import org.springframework.ui.Model;
import ua.lyashko.module3.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers ();

    void saveUser ( User user );

    User getUserById ( Integer id );

    User updateUser ( User user );

    String userValidation ( User user );

    void resetPassword ( User user );

    boolean loginValidation ( User user, Model model );
}
