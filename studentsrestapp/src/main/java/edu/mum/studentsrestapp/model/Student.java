package edu.mum.studentsrestapp.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {


    private String firstName;
    private String lastName;
    private int age;
    private double gpa;
}
