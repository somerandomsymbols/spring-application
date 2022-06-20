package com.project.springapplication.journal;

import com.project.springapplication.student.Student;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "journals")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String disciplineName;

    @Column(nullable = false, length = 25)
    private String professorName;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Integer mark;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "studentid", referencedColumnName = "id")
    private Student studentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", disciplineName='" + disciplineName + '\'' +
                ", professorName='" + professorName + '\'' +
                ", mark=" + mark +
                ", studentId=" + studentId +
                '}';
    }
}
