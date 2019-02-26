package com.gandalf;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
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
    }
}
