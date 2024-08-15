package test;

import com.example.assignment.models.User;
import com.example.assignment.repositories.UserRepository;
import com.example.assignment.utils.DBCon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class UserRepositoryTest {

    private static Connection connection;

    @BeforeAll
    public static void setUpDB(){
        try {
            connection = DriverManager.getConnection(DBCon.JDBC_URL,DBCon.DB_USERNAME,DBCon.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute("drop table if exists SUSERS");
            String sql = "Create table SUSERS (USER_ID int primary key, USER_GUID varchar(50), USER_NAME varchar(50))";
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @BeforeEach
    public void cleanDB(){
        try {
            String sql = "DELETE from SUSERS";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Test
    public void testGetAllUsersEmptyDB(){
        UserRepository userRepository = new UserRepository();
        userRepository.openConnection();
        List<User> users = userRepository.getAllUsers();
        userRepository.closeConnection();
        Assertions.assertEquals(0,users.size());
    }

    @Test
    public void getTwoUsersFromDB(){
        UserRepository userRepository = new UserRepository();
        userRepository.openConnection();
        userRepository.addUser(new User(1,"a1", "Ivan"));
        userRepository.addUser(new User(2,"a2", "Marek"));
        List<User> users = userRepository.getAllUsers();
        userRepository.closeConnection();
        Assertions.assertEquals(2,users.size());
    }

    @Test
    public void testAddUserAnGetUser(){
        UserRepository userRepository = new UserRepository();
        userRepository.openConnection();
        User user = new User(1,"aa","Ivan");
        userRepository.addUser(user);
        List<User> users = userRepository.getAllUsers();
        userRepository.closeConnection();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(user.getId(), users.get(0).getId());
        Assertions.assertEquals(user.getGuid(), users.get(0).getGuid());
        Assertions.assertEquals(user.getName(), users.get(0).getName());
    }

    @Test
    public void testDeleteAllUsers(){
        UserRepository userRepository = new UserRepository();
        userRepository.openConnection();
        User user = new User(1,"aa","Ivan");
        userRepository.addUser(user);
        userRepository.deleteAll();
        List<User> users = userRepository.getAllUsers();
        Assertions.assertEquals(0, users.size());
    }
}
