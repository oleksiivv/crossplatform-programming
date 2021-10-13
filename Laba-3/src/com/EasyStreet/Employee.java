package com.EasyStreet;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Employee {

    private String surname;
    private Position position;
    private LocalDate enrollmentDate;

    public Employee(){}

    public Employee(String surname, Position position, String enrollmentDate){
        this.surname = surname;
        this.position = position;
        this.enrollmentDate = LocalDate.parse(enrollmentDate);
    }

    public Employee setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Employee setPosition(Position position) {
        this.position = position;
        return this;
    }

    public Employee setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = LocalDate.parse(enrollmentDate);
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Position getPosition() {
        return position;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public static Employee readFromFile(Scanner fread){
        if(fread.hasNextLine()) {
            String surname = fread.nextLine();
            Position position = Position.valueOf(fread.nextLine());
            String date = fread.nextLine();

            return new Employee(surname, position, date);
        }
        return null;
    }
}
