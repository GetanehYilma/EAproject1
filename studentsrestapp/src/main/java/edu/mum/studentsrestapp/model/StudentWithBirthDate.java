package edu.mum.studentsrestapp.model;

import lombok.*;

import java.sql.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentWithBirthDate {

    private String firstName;
    private String lastName;
    private Date birthdate;
    private double gpa;
}
