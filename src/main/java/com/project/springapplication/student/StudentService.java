package com.project.springapplication.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    @Autowired private StudentRepository repo;

    public List<Student> listAll()
    {
        return (List<Student>) repo.findAll();
    }

    public void save(Student student) {
        repo.save(student);
    }

    public Student getStudentById(int studentId) throws StudentNotFoundException {
        Optional<Student> student = repo.findById(studentId);
        if (student.isPresent())
        return student.get();
        throw new StudentNotFoundException("Student with this id doesn't exist");
    }

    public void delete(int studentId) throws StudentNotFoundException {
        Long count = repo.countById(studentId);
        if (count == null|| count == 0){
            throw new StudentNotFoundException("Student with this id doesn't exist");
        }
        repo.deleteById(studentId);
    }
}
