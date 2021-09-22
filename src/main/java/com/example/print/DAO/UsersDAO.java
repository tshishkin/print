package com.example.print.DAO;

import com.example.print.Model.User;

import java.util.List;

public interface UsersDAO {
    List<User> getAllUsers();

    void addNewUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);
}
