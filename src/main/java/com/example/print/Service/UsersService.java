package com.example.print.Service;

import com.example.print.Model.User;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface UsersService {
    List<User> getAll();

    void addNewUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    byte[] drawPass(User user) throws IOException;
}
