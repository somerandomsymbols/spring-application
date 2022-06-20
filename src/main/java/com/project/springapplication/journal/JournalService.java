package com.project.springapplication.journal;

import com.project.springapplication.student.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired private JournalRepository repo;

    public List<Journal> listAll()
    {
        return (List<Journal>) repo.findAll();
    }

    public void save(Journal journal) {
        repo.save(journal);
    }

    public Journal getJournalById(int journalId) throws StudentNotFoundException {
        Optional<Journal> journal = repo.findById(journalId);
        if (journal.isPresent())
            return journal.get();
        throw new StudentNotFoundException("Journal with this id doesn't exist");
    }

    public List<Journal> listByStudentId(int studentId) {
        List<Journal> listJournal = (List<Journal>) repo.findAll();
        listJournal.removeIf(j -> j.getStudentId().getId() != studentId);
        return listJournal;
    }

    public void delete(int journalId) throws StudentNotFoundException {
        Long count = repo.countById(journalId);
        if (count == null|| count == 0){
            throw new StudentNotFoundException("Journal with this id doesn't exist");
        }
        repo.deleteById(journalId);
    }
}
