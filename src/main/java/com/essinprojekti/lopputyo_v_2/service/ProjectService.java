package com.essinprojekti.lopputyo_v_2.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.essinprojekti.lopputyo_v_2.data.BlendedLearningStudent;
import com.essinprojekti.lopputyo_v_2.data.Course;
import com.essinprojekti.lopputyo_v_2.data.DayLearningStudent;
import com.essinprojekti.lopputyo_v_2.data.OpenUasStudent;
import com.essinprojekti.lopputyo_v_2.data.Student;

@Service
public class ProjectService {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public ProjectService() {
        // luodaan valmiiksi opiskelijoita
        students.add(new DayLearningStudent("Essi", 26, 12333, 2022));
        students.add(new DayLearningStudent("Keijo", 55, 65455, 2005));
        students.add(new BlendedLearningStudent("Asko", 27, 65423, 2023));
        students.add(new OpenUasStudent("Reijo", 45, 635763, 2014));
        students.add(new OpenUasStudent("Anja", 41, 6546546, 1998));
        students.add(new OpenUasStudent("Tuula", 37, 45677, 2009));
        courses.add(new Course(656565, "Web-ohjelmointi", 2));
    }

    public boolean addDayLearningStudent(DayLearningStudent s) {
        try {
            students.add(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addBlendedLearningStudent(BlendedLearningStudent s) {
        try {
            students.add(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addOpenUasStudent(OpenUasStudent s) {
        try {
            students.add(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Luodaan kopio students-listasta, jotta käyttäjä ei pääse käsiksi suoraan
                                          // alkuperäiseen students-listaan
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getGraduatedStudents(int year) {
        List<Student> graduatedStudents = new ArrayList<>();
        OpenUasStudent o = new OpenUasStudent();
        
            
         // avoimen amkin opiskelijalla ei ole valmistumisvuotta
            for (Student student : students) {
                if (student.getClass() != o.getClass() && (student.getFirstYear() + 4) <= year) {
                    graduatedStudents.add(student);
                }
            }

            return new ArrayList<>(graduatedStudents);
    }

    public List<Student> getAllDayStudents() {
        List<Student> dayStudents = new ArrayList<>();
        DayLearningStudent d = new DayLearningStudent();
        for (Student student : students) {
            if (student.getClass() == d.getClass()) {
                dayStudents.add(student);
            }
        }
        return new ArrayList<>(dayStudents);
    }

    public List<Student> getAllBlendedStudents() {
        List<Student> blendedStudents = new ArrayList<>();
        BlendedLearningStudent b = new BlendedLearningStudent();
        for (Student student : students) {
            if (student.getClass() == b.getClass()) {
                blendedStudents.add(student);
            }
        }
        return new ArrayList<>(blendedStudents);
    }

    public List<Student> getAllOuasStudents() {
        List<Student> OuasStudents = new ArrayList<>();
        OpenUasStudent o = new OpenUasStudent();
        for (Student student : students) {
            if (student.getClass() == o.getClass()) {
                OuasStudents.add(student);
            }
        }
        return new ArrayList<>(OuasStudents);
    }

    public Map<String, Object> getBasicInfo() {

        Map<String, Object> data = new HashMap<>();

        data.put("Number of students", students.size());

        double ages = 0;
        double averageAge = 0;

        int dStudents =0;
        int bStudents =0;
        int oStudents =0;

        for (Student student : students) {
            DayLearningStudent d = new DayLearningStudent();
            BlendedLearningStudent b = new BlendedLearningStudent();
            OpenUasStudent o = new OpenUasStudent();

            if(student.getClass() == d.getClass()){
                dStudents += 1;
            }

            else if (student.getClass() == b.getClass()){
                bStudents +=1;
            }

            else if (student.getClass() == o.getClass()){
                oStudents +=1;
            }            
        }

        data.put("Day learning students", dStudents);
        data.put("Blended learning students", bStudents);
        data.put("Open UAS students", oStudents);

        for (Student student : students) {
            ages += student.getAge();
        }

        averageAge = ages / students.size();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        try {

            data.put("average age", nf.format(averageAge));
            return data;

        } catch (IllegalArgumentException e) {
            data.put("average age", null);
            return data;
        }
    }

    public boolean updateStudentName(Student student) {
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

    public boolean addCourse(Course c) {
        try {
            courses.add(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addStudentToCourse(Student s, int id) {
        Student student = getStudentById(s.getStudentId());
        for (Course course : courses) {
            if(course.getCourseId()==id && student != null){
                course.setCourseStudents(student);
                return true;
            }
        } 
        return false;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses); // Luodaan kopio students-listasta, jotta käyttäjä ei pääse käsiksi suoraan
                                          // alkuperäiseen students-listaan
    }

    public Course getCourseById(int id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                return course;
            }
        }
        return null;
    }

    public List<Student> getCourseStudents(int id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                return course.getCourseStudents();
            }
        }
        return null;
    }

    //Lisää put toiminto

    public boolean removeCourse(int id) {
        Course c = getCourseById(id);

        if (c != null) {
            return courses.remove(c);
        } else {
            return false;
        }

    }


}
