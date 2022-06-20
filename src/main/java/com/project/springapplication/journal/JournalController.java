package com.project.springapplication.journal;

import com.project.springapplication.student.StudentNotFoundException;
import com.project.springapplication.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class JournalController {
    @Autowired private JournalService service;
    @Autowired private StudentService serviceStudent;

    @GetMapping("/journals/new_journal/{id}")
    public String newJournalForm(@PathVariable("id") int studentId, Model model, RedirectAttributes ra) {
        //try
        //{
            Journal journal = new Journal();
            //journal.setStudentId(serviceStudent.getStudentById(studentId));
            model.addAttribute("journal", journal);
            model.addAttribute("studentId", studentId);
            model.addAttribute("pageTitle", "Add new mark");
            return "new_journal_form";
        //}
        /*catch (StudentNotFoundException e)
        {
            ra.addFlashAttribute("message", "Student with this id doesn't exist");
            return "redirect:/students/journal/" + studentId;
        }*/

    }

    @PostMapping("/journals/save/{id}")
    public String saveJournal(@PathVariable("id") int studentId, Journal journal, RedirectAttributes ra){
        try
        {
            journal.setStudentId(serviceStudent.getStudentById(studentId));
            service.save(journal);
            ra.addFlashAttribute("message", "New mark added to list");
            return "redirect:/students/journals/" + studentId;
        }
        catch (StudentNotFoundException e)
        {
            ra.addFlashAttribute("message", "Student with this id doesn't exist");
            return "redirect:/students/journals/" + studentId;
        }
    }

    @GetMapping("/journals/edit/{id}")
    public String editJournalForm(@PathVariable("id") int journalId, Model model, RedirectAttributes ra)  {
        try {
            Journal journal = service.getJournalById(journalId);
            model.addAttribute("journal", journal);
            model.addAttribute("studentId", journal.getStudentId().getId());
            model.addAttribute("pageTitle", "Edit mark (ID: " + journalId + ")" );
            return "new_journal_form";
        } catch(StudentNotFoundException e){
            ra.addFlashAttribute("message", "Journal with this id doesn't exist");
            return "redirect:/students";
        }

    }

    @GetMapping("/journals/delete/{id}")
    public String deleteJournal(@PathVariable("id") int journalId, Model model, RedirectAttributes ra)
    {
        try {
            Journal journal = service.getJournalById(journalId);
            service.delete(journalId);
            ra.addFlashAttribute("message", "Journal (ID: " + journalId + ") deleted succesfully");
            return "redirect:/students/journals/" + journal.getStudentId().getId();
        } catch(StudentNotFoundException e){
            ra.addFlashAttribute("message", "Journal with this id doesn't exist");
        }

        return "redirect:/students";
    }

    @GetMapping("/students/journals/{id}")
    public String getJournals(@PathVariable("id") int studentId, Model model, RedirectAttributes ra)
    {
        List<Journal> listJournal = service.listByStudentId(studentId);
        model.addAttribute("listJournal", listJournal);
        model.addAttribute("studentId", studentId);
        return "journal";
    }
}
