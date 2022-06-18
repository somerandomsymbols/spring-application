package com.project.springapplication.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {
    @Autowired private StudentService service;

    @GetMapping("/students")
    public String showStudentList(Model model)
    {
        List<Student> listStudents = service.listAll();
        model.addAttribute("listStudents", listStudents);
        return "students";
    }
    @GetMapping("/students/new_student")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add new student");
        return "new_student_form";
    }
    @PostMapping("/students/save")
    public String saveStudent(Student student, RedirectAttributes ra){
        service.save(student);
        ra.addFlashAttribute("message", "New student added to list");
        return "redirect:/students";
    }
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable("id") int studentId, Model model, RedirectAttributes ra)  {
        try {
            Student student = service.getStudentById(studentId);
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edit student (ID: " + studentId + ")" );
            return "new_student_form";
        } catch(StudentNotFoundException e){

            ra.addFlashAttribute("message", "Student with this id doesn't exist");
            return "redirect:/students";
        }

    }
    @GetMapping("/students/delete/{id}")
     public String deleteStudent(@PathVariable("id") int studentId, Model model, RedirectAttributes ra)
    {
        try {
            service.delete(studentId);
            ra.addFlashAttribute("message", "Student (ID: " + studentId + ") deleted succesfully");
        } catch(StudentNotFoundException e){
            ra.addFlashAttribute("message", "Student with this id doesn't exist");

        }finally {
            return "redirect:/students";
        }
    }
}
