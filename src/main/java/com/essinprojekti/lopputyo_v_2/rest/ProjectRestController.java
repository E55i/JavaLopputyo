package com.essinprojekti.lopputyo_v_2.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.essinprojekti.lopputyo_v_2.data.BlendedLearningStudent;
import com.essinprojekti.lopputyo_v_2.data.Course;
import com.essinprojekti.lopputyo_v_2.data.DayLearningStudent;
import com.essinprojekti.lopputyo_v_2.data.OpenUasStudent;
import com.essinprojekti.lopputyo_v_2.data.Student;
import com.essinprojekti.lopputyo_v_2.service.ProjectService;

@RestController
public class ProjectRestController {

    ProjectService pc;

    @Autowired
    public ProjectRestController(ProjectService pc) {
        this.pc = pc;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return pc.getAllStudents();
    }

    @GetMapping("/daystudents")
    public List<Student> getDayStudents() {
        return pc.getAllDayStudents();
    }

    @GetMapping("/blendedstudents")
    public List<Student> getBlendedStudents() {
        return pc.getAllBlendedStudents();
    }

    @GetMapping("/ouasstudents")
    public List<Student> getOuasStudents() {
        return pc.getAllOuasStudents();
    }
    //haetaan annettuun vuoteen mennessä valmistuneet opiskelijat
    @GetMapping("/graduatedstudents/{year}") 
    public ResponseEntity<List<Student>> getGraduatedStudents(@PathVariable int year) {
        List <Student> gS = pc.getGraduatedStudents(year);
        if(gS.size() != 0){
            return new ResponseEntity<>(gS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);   
    }

    @GetMapping("/data")
    public Map<String, Object> getStudentInfo() {
        return pc.getBasicInfo();
    }

    // Hae yksi opiskelija opiskelijanumerolla
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student s = pc.getStudentById(id);
        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/dl_student") 
    public String addDayLearningStudent(@RequestBody DayLearningStudent student) {
        if (pc.addDayLearningStudent(student) == true) {
            return "Day learning student added";
        }
        return "Something went wrong!";

    }

    @PostMapping("/bl_student") 
    public String addBlendedLearningStudent(@RequestBody BlendedLearningStudent student) {

        if (pc.addBlendedLearningStudent(student) == true) {
            return "Blended learning student added";
        }
        return "Something went wrong!";
    }

    @PostMapping("/oa_student") 
    public String addOpenUasStudent(@RequestBody OpenUasStudent student) {

        if (pc.addOpenUasStudent(student) == true) {
            return "Open UAS student added";
        }
        return "Something went wrong!";
    }

    @PutMapping("/updatename")
    public String updateName(@RequestBody Student student) {
        if (pc.updateStudentName(student) == true) {
            return "Name updated!";
        } else {
            return "Name couldn't be updated!";
        }
    }

    @DeleteMapping("/deletestudent")
    public String deleteStudent(@RequestBody Student student) {
        if(pc.removeStudent(student.getStudentId())==true){
        return "Student removed";
        }
        else {
            return "Couldn't remove student";
        }
    }

    @PostMapping("/addcourse")
    public String addCourse(@RequestBody Course course) {
        if (pc.addCourse(course) == true) {
            return "Course added";
        }
        return "Something went wrong!";

    }

    @PostMapping("/studenttocourse/{id}")
    public String addStudentToCourse(@RequestBody Student student, @PathVariable int id) {
        if (pc.addStudentToCourse(student, id) == true) {
            return "Student added to course";
        }
        return "Something went wrong!";

    }


    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return pc.getAllCourses();
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Course c = pc.getCourseById(id);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getstudents/{id}")
    public ResponseEntity<List<Student>> getCourseStudents(@PathVariable int id) {
        List <Student> cS = pc.getCourseStudents(id);
        if(cS.size() != 0){
            return new ResponseEntity<>(cS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

    //Lisää remove Course ja jokin päivitys Course luokkaan
}
