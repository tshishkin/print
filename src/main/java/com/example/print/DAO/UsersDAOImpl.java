package com.example.print.DAO;

import com.example.print.Model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UsersDAOImpl extends AbstractDAO implements UsersDAO {

    private static final RowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    public UsersDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from users order by creation_date", ROW_MAPPER);
    }

    @Override
    public void addNewUser(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update("insert into users (id," +
                " first_name," +
                " last_name," +
                " middle_name," +
                " passport_attributes," +
                " creation_date) " +
                "VALUES " +
                "(nextval('users_seq'),\n" +
                " :firstName," +
                " :lastName," +
                " :middleName," +
                " :passportAttributes," +
                " now())", params );
    }

    @Override
    public void deleteUser(Long id) {
        jdbcTemplate.update("delete from users where id = :id", map("id", id ) );
    }

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject("select * from users where id = :id ", map("id", id), ROW_MAPPER);
    }
}
