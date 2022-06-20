package com.project.springapplication;

import com.project.springapplication.journal.Journal;
import com.project.springapplication.journal.JournalRepository;
import com.project.springapplication.student.Student;
import com.project.springapplication.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class JournalRepositoryTest {
    @Autowired private JournalRepository repo;
    @Autowired private StudentRepository repoStudent;

    @Test
    public void testAddNew()
    {
        Student student = repoStudent.findById(6).get();
        Journal journal = new Journal();

        journal.setStudentId(student);
        journal.setDisciplineName("Parasha");
        journal.setProfessorName("Pituh");
        journal.setMark(60);

        Journal savedJournal = repo.save(journal);

        assertThat(savedJournal).isNotNull();
        assertThat(savedJournal.getId()).isGreaterThan(0);

        student = repoStudent.findById(6).get();
        assertThat(student.getJournals()).isNotEmpty();
    }
}
