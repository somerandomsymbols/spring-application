package com.project.springapplication;

import com.project.springapplication.student.Student;
import com.project.springapplication.student.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTests {
    @Autowired private StudentRepository repo;

    @Test
    public void testAddNew()
    {
        Student student = new Student();

        student.setFirstName("Cyka");
        student.setLastName("Pidor");

        Student savedStudent = repo.save(student);

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll()
    {
        Iterable<Student> students = repo.findAll();
        assertThat(students).hasSizeGreaterThan(0);

        for (Student student : students)
        {
            System.out.println(student);
        }
    }

    @Test
    public void testUpdate()
    {
        Integer studentId = 3;
        String firstName = "Natural";
        Optional<Student> optionalStudent = repo.findById(studentId);
        Student student = optionalStudent.get();
        student.setFirstName(firstName);
        repo.save(student);

        Student updatedStudent = repo.findById(studentId).get();
        assertThat(updatedStudent.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void testGet()
    {
        Integer studentId = 3;
        Optional<Student> optionalStudent = repo.findById(studentId);
        assertThat(optionalStudent).isPresent();
        System.out.println(optionalStudent.get());
    }

    @Test
    public void testDelete()
    {
        Integer studentId = 3;
        repo.deleteById(studentId);

        Optional<Student> optionalStudent = repo.findById(studentId);
        assertThat(optionalStudent).isNotPresent();
    }
}
