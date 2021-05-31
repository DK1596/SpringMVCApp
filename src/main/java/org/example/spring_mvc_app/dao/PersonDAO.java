package org.example.spring_mvc_app.dao;

import org.example.spring_mvc_app.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int count;
    private static final String URL = "jdbc:postgresql://localhost:5432/db_people";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sql6of1";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index(){
     List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM people";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    public Person show(int id){
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person){
//        person.setId(++count);
//        people.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO people VALUES("+1+",'"+person.getName()+"',"+24+", '"+person.getEmail()+"')";

            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    public void update(int id, Person updatedPerson){
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
//    }
//
//    public void delete(int id){
//        people.removeIf(p -> p.getId() == id);
//    }
}
