package com.rmnlcn.Spring_CRUD_MVC.entities;

import jakarta.persistence.*;

@Entity
@Table(name="wrestler")
public class Wrestler {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="alias")
    private String alias;

    @Column(name="age")
    private int age;

    @Column(name="weight")
    private float weight;

    @Column(name="height")
    private String height;


    // define constructors

    public Wrestler() {}

    public Wrestler(String firstName, String lastName, String alias, int age, float weight, String height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }


    // define getters/setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    // define toString

    @Override
    public String toString() {
        return "Wrestler{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alias='" + alias + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height='" + height + '\'' +
                '}';
    }
}
