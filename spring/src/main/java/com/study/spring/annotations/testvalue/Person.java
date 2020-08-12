/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testvalue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author study
 * @version : Person.java, v 0.1 2020年08月11日 22:32 study Exp $
 */
@Component
public class Person {
    @Value("司马")
    private String firstName;
    @Value("#{28-8}")
    private Integer age;
    @Value("${person.lastName}")
    private String lastName;

    /**
     * Getter method for property <tt>firstName</tt>.
     *
     * @return property value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for property <tt>firstName</tt>.
     *
     * @param firstName value to be assigned to property firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Getter method for property <tt>lastName</tt>.
     *
     * @return property value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for property <tt>lastName</tt>.
     *
     * @param lastName value to be assigned to property lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Person [");
        builder
                .append("        firstName=").append(firstName)
                .append(",        age=").append(age)
                .append(",        lastName=").append(lastName)
                .append(']');
        return builder.toString();
    }
}