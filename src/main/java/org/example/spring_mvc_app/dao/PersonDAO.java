package org.example.spring_mvc_app.dao;

import org.example.spring_mvc_app.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int count;

    {
        people = new ArrayList<>();
        people.add(new Person(++count, "Tom", 24, "tom@mail.ru"));
        people.add(new Person(++count, "Bill", 45, "bill@gmail.com"));
        people.add(new Person(++count, "Mike", 65, "mike@yahoo.com"));
        people.add(new Person(++count, "Kate", 20, "kate@mail.ru"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++count);
        people.add(person);
    }

    public void update(int id, Person updatedPerson){
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
