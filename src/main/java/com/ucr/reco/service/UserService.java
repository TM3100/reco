package com.ucr.reco.service;

import com.ucr.reco.dto.UserDTO;
import com.ucr.reco.model.User;
import com.ucr.reco.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    /*
        public User add(User user){
            if(repository.existsByEmail(user.getEmail())){
                return null;
            }
            return repository.save(user);
        }
    */
    public User add(UserDTO user) {
        if (repository.existsByEmail(user.getEmail())) {
            return null;
        } else {
            if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getRole() == null) {
                return null;
            }
        }
        User userBD = new User();
        userBD.setName(user.getName());
        userBD.setEmail(user.getEmail());
        userBD.setPassword(user.getPassword());
        userBD.setRole(user.getRole());
        return repository.save(userBD);
        //return "Proceso exitoso";
    }

    public User getById(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
//        User user = repository.findById(id.intValue());
//        if (user != null) {
//            return user;
//        }
        /*if (repository.existsById(id)) {
            return repository.findById(id).get();
        }*/
        return null;
    }

    public User findByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
//        User user = repository.findByEmail(email);
//        if (user != null) {
//            return user;
//        }
        return null;
    }

    public User update(User user) {
        User userExits = repository.getByEmail(user.getEmail());
        if (userExits != null) {
            if (user.getName() != null) {
                userExits.setName(user.getName());
            }
            if (user.getPassword() != null) {
                userExits.setPassword(user.getPassword());
            }
            if (user.getRole() != null) {
                userExits.setRole(user.getRole());
            }

        } else {
            return null;
        }
        return repository.save(userExits);
    }

    public User delete(Integer id) {
        Optional<User> userExits = repository.findById(id);
        if (userExits.isPresent()) {
            repository.deleteById(id);
            return (User) userExits.get();
        } else {
            return null;
        }
    }

    public User changePassword(String email, String newPassword) {
        User userExits = repository.getByEmail(email);
        if (userExits != null) {
            userExits.setPassword(newPassword);
            return repository.save(userExits);
        } else {
            return null;
        }
    }

    public boolean login(String email, String password) {
        Optional<User> userOptional = repository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
//        User user = repository.findByEmail(email);
//        if (user != null && user.getPassword().equals(password)) {
//            return true;
//        }
        return false;
    }


}
