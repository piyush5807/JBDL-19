package com.basic;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Person {

    int id;
    String firstName;
    String lastName;
    int age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    // p1 p2
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age); // 3, "a", "z", 40 - p1
    }

    public static void main(String[] args) throws InterruptedException {

        SingletonClass obj = SingletonClass.getInstance();
        System.out.println(obj);

        setFunction();

        HashMap<Integer, Person> personMap = new HashMap<>();

        personMap.put(1, new Person(1, "Joe", "Root", 30));
        personMap.put(2, new Person(2, "Virat", "Kohli", 32));


        System.out.println(personMap);

        Person p1 = new Person(1, "Joe", "Wilson", 45);
        Person p2 = new Person(1, "Joe", "Wilson", 40);

        System.out.println(p1.equals(p2));

        HashMap<Person, Integer> hashMap = new HashMap<>();
        hashMap.put(p1, 1);
        hashMap.put(p2, 2);

        // p1 = p2 (on basis of props)
        System.out.println(p1.hashCode() + " " + p2.hashCode());

        String s1 = "abc";
        String s2 = "abd";

        System.out.println(s1.hashCode() + " " + s2.hashCode());

        System.out.println(s1.equals(s2));


        System.out.println(hashMap);

    }

    public static void setFunction(){

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(8);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(5);

        System.out.println(hashSet);

    }
}
