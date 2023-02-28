package com.essinprojekti.lopputyo_v_2.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.essinprojekti.lopputyo_v_2.data.BlendedLearningStudent;
import com.essinprojekti.lopputyo_v_2.data.DayLearningStudent;
import com.essinprojekti.lopputyo_v_2.data.OpenUasStudent;
import com.essinprojekti.lopputyo_v_2.data.Student;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public StudentService() {
        // luodaan valmiiksi opiskelijoita
        students.add(new DayLearningStudent("Essi", 26, 12333, 2022));
        students.add(new DayLearningStudent("Keijo", 55, 65455, 2005));
        students.add(new BlendedLearningStudent("Asko", 27, 65423, 2023));
        students.add(new OpenUasStudent("Reijo", 45, 635763, 2014));
        students.add(new OpenUasStudent("Anja", 41, 6546546, 1998));
        students.add(new OpenUasStudent("Tuula", 37, 45677, 2009));
    }

    // Tee hakuehotja niin että hakee vaikkapa ne opsikelijat joiden
    // valmistumisvuosi on mennyt!

    public void addDayLearningStudent(DayLearningStudent s) {
        students.add(s);
    }

    public void addBlendedLearningStudent(BlendedLearningStudent s) {
        students.add(s);
    }

    public void addOpenUasStudent(OpenUasStudent s) {
        students.add(s);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Luodaan kopio students-listasta, jotta käyttäjä ei pääse käsiksi suoraan
                                          // alkuperäiseen students-listaan
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getStudentId() == id) { // käydään läpi kaikki listan oliot
                return student;
            }
        }
        return null; // Jos ei löydy, palauttaa null
    }

    public Student getGraduatedStudents(int year) {
        OpenUasStudent o = new OpenUasStudent();
        for (Student student : students) {
            if (student.getClass() != o.getClass() && (student.getFirstYear() + 4) <= year) {
                return student;
            }
        }
        return null; // Jos ei löydy, palauttaa null
    }

    public Student getAllDayStudents() {
        DayLearningStudent d = new DayLearningStudent();
        for (Student student : students) {
            if (student.getClass() == d.getClass()) {
                return student;
            }
        }
        return null;
    }

    public Student getAllBlendedStudents() {
        BlendedLearningStudent b = new BlendedLearningStudent();
        for (Student student : students) {
            if (student.getClass() == b.getClass()) {
                return student;
            }
        }
        return null;
    }

    public Student getAllOuasStudents() {
        OpenUasStudent o = new OpenUasStudent();
        for (Student student : students) {
            if (student.getClass() == o.getClass()) {
                return student;
            }
        }
        return null;
    }

    public Map<String, Object> getBasicInfo() {

        Map<String, Object> data = new HashMap<>();

        data.put("count", students.size());

        double ages = 0;
        double averageAge = 0;

        for (Student student : students) {
            ages += student.getAge();
        }

        averageAge = ages / students.size();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        data.put("average age", nf.format(averageAge));

        return data;
    }

    public boolean updateStudentName(Student student ) {
        Student s = getStudentById(student.getStudentId());

        if (s != null) {
            s.setName(student.getName());
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent(int id) {
        Student s = getStudentById(id);

        if (s != null) {
            return students.remove(s);
        } else {
            return false;
        }
    }

}
