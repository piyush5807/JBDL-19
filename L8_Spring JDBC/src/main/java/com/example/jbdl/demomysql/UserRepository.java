package com.example.jbdl.demomysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository { // directly communicating with the db

    private static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private Connection connection;
//    private static UserRepository userRepository;

    private UserRepository(@Value("${my_db.username}") String username,
                           @Value("${my_db.password}") String password) throws SQLException {
        connection = getConnection(username, password);
        createTable();
//        createTableStatic();
    }

//    public UserRepository getInstance() throws SQLException {
//        if(userRepository == null){
//            userRepository = new UserRepository();
//        }
//
//        return userRepository;
//    }
//
//    public static void createTableStatic() throws SQLException {
//        Statement statement = connection.createStatement();
//        boolean result = statement.execute("create table if not exists user(id int primary key, name varchar(20), " +
//                "country varchar(20), age int)");
//        logger.info("table is create - {}", result);
//    }
//

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        boolean result = statement.execute("create table if not exists user(id int primary key, name varchar(20), " +
                "country varchar(20), age int)");
        logger.info("table is create - {}", result);
    }

    private Connection getConnection(String username, String password) throws SQLException {
        logger.info("username - {}, password - {}", username, password );
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/users_db", username , password);
    }

    public void saveUser(User user) throws SQLException {
        // insert into user(id, name, country) values();
        PreparedStatement statement = connection.prepareStatement("insert into user(id, name, country, age) VALUES (?, ?, ?, ?)");

        statement.setInt(1, user.getId());
        statement.setString(2, user.getName());
        statement.setString(3, user.getCountry());
        statement.setInt(4, user.getAge());

        int result = statement.executeUpdate();
        logger.info("number of users inserted - {}", result);
    }

    // id   name  country    age

    public List<User> getUsers() throws SQLException {
        // select * from user;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");

        logger.info("got the result set - {}", resultSet);

        List<User> userList = new ArrayList<>();

        while(resultSet.next()){

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String country = resultSet.getString("country");
            int age = resultSet.getInt("age");

            User user = User.builder()
                    .age(age)
                    .id(id)
                    .name(name)
                    .country(country)
                    .build();

            userList.add(user);
        }

        return userList;
    }

    public User getUser(int id) throws SQLException{
        // select * from user where id = id

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where id = " + id);

        logger.info("got the result set - {}", resultSet);

        resultSet.next();
        int userId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        int age = resultSet.getInt("age");

        return User.builder()
                .age(age)
                .id(userId)
                .name(name)
                .country(country)
                .build();

    }
}
