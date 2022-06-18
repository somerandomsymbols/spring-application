package com.project.springapplication.student;

public class StudentNotFoundException extends Throwable {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
