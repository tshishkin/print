package com.example.print.Service;

import com.example.print.DAO.UsersDAO;
import com.example.print.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersDAO usersDAO;

    @Override
    public List<User> getAll() {
        return usersDAO.getAllUsers();
    }

    @Override
    public void addNewUser(User user) {
        usersDAO.addNewUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        usersDAO.deleteUser(id);
    }

    @Override
    public User getUserById(Long id) { return usersDAO.getUserById(id);}

    @Override
    public byte[] drawPass(User user) throws IOException {
        Resource resource = new ClassPathResource("/static/img/passTemplate.png");
        BufferedImage image = ImageIO.read(resource.getFile());
        Font font = new Font("Arial", Font.BOLD, 12);
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy")
                .format(Calendar.getInstance().getTime());
        Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        //Первый блок даты
        graphics.drawString(timeStamp, 120, 120);
        //Первый блок инициалов
        graphics.drawString(user.getLastName(), 60, 247 );
        graphics.drawString(user.getFirstName(), 60, 263);
        graphics.drawString(user.getMiddleName(), 60, 279);
        //Первый блок к кому
        graphics.drawString("Дмитрий Сергеев", 110, 355);
        //Первый блок названия организации
        graphics.drawString("Компьютер +", 120, 327);
        //Второй блок даты
        graphics.drawString(timeStamp, 348, 75);
        //Второй блок инициалов
        graphics.drawString(user.getLastName(), 305, 160 );
        graphics.drawString(user.getFirstName(), 305, 176);
        graphics.drawString(user.getMiddleName(), 305, 192);
        //Cерия/Номер паспорта
        graphics.drawString(user.getPassportAttributes(), 305, 225);
        //Второй блок названия организации
        graphics.drawString("Компьютер +", 370, 285);
        //Второй блок к кому
        graphics.drawString("Дмитрий Сергеев", 370, 320);
        //Третий блок даты
        graphics.drawString(timeStamp, 630, 82);
        //Третий блок инициалов
        graphics.drawString(user.getLastName(), 565, 175 );
        graphics.drawString(user.getFirstName(), 565, 210);
        graphics.drawString(user.getMiddleName(), 565, 245);
        //Второй блок названия организации
        graphics.drawString("Компьютер +", 620, 330);
        //Третий блок к кому
        graphics.drawString("Дмитрий Сергеев", 620, 375);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}
