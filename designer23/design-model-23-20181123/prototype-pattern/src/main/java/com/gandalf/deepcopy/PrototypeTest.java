package com.gandalf.deepcopy;

import com.gandalf.deepcopy.Person;

import java.io.IOException;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Person person = new Person();
        person.setName("tomcat");
        person.setState(true);
        person.setInterest(person.new Interest("swim"));

        Person clonePerson = (Person) person.clone();
        clonePerson.setName("weblogic");
        clonePerson.setState(false);
        clonePerson.interest.setInterestName("pingpang");
        System.out.println(clonePerson);
        System.out.println(person);

        Person c2 = person.deepCloneByIO();
        c2.setName("tongweb");
        c2.setState(false);
        c2.interest.setInterestName("football");
        System.out.println("----------------");
        System.out.println(c2);
        System.out.println(person);
    }
}
