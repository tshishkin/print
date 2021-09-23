package com.example.print.DAO;

import com.example.print.Model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UsersDAOImpl implements UsersDAO {

    protected final NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    public UsersDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from users order by id", ROW_MAPPER);
    }

    @Override
    public void addNewUser(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update("insert into users (id, first_name, last_name, middle_name, passport_attributes, creation_date) VALUES (nextval('users_seq'),\n" +
                " :firstName, :lastName, :middleName, :passportAttributes, now())", params );
    }

    @Override
    public void deleteUser(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update("delete from users where id = :id", params );
    }

    @Override
    public User getUserById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate.queryForObject("select * from users where id = :id ", params, ROW_MAPPER);
    }


}
