package ua.lyashko.module3.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ua.lyashko.module3.model.User;
import ua.lyashko.module3.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl ( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers () {
        return (List<User>) userRepository.findAll ( );
    }

    @Override
    public void saveUser ( User user ) {
        userRepository.save ( user );
    }

    @Override
    public User getUserById ( Integer id ) {
        return userRepository.findById ( id ).get ( );
    }

    @Override
    public User updateUser ( User user ) {
        return userRepository.save ( user );
    }

    @Override
    public String userValidation ( User user ) {
        List<User> users = (List<User>) userRepository.findAll ( );
        String name = "unknown user";
        for (User temp : users) {
            if (user.getLogin ( ).equals ( temp.getLogin ( ) ) && user.getPassword ( ).equals ( temp.getPassword ( ) )) {
                name = temp.getName ( );
                break;
            }
        }
        return name;
    }

    @Override
    public void resetPassword ( User user ) {
        List<User> users = (List<User>) userRepository.findAll ( );
        for (User temp : users) {
            if (user.getLogin ( ).equals ( temp.getLogin ( ) )) {
                User u = new User ( );
                u.setLogin ( temp.getLogin ( ) );
                u.setPassword ( user.getPassword ( ) );
                u.setName ( temp.getName ( ) );
                u.setCity ( temp.getCity ( ) );
                userRepository.save ( u );
            }
        }
    }

    @Override
    public boolean loginValidation ( User user, Model model ) {
        boolean flag = false;
        List<User> users = (List<User>) userRepository.findAll ( );
        for (User temp : users) {
            if (user.getLogin ( ).equals ( temp.getLogin ( ) )) {
                flag = true;
                model.addAttribute ( "message", "Sorry, this login already exists" );
                break;
            }
        }
        return flag;
    }
}
