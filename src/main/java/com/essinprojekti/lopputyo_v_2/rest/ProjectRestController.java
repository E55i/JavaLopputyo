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

    // katso luentotallenne 25 kohta 27.00 (tee ensin saman luennon ohjeilla service
    // luokka valmiiksi)

   
    @GetMapping("/students")
    public List<Student> getStudents() {
        return pc.getAllStudents();
    }

    @GetMapping("/daystudents")
    public Student getDayStudents() {
        return pc.getAllDayStudents();
    }

    @GetMapping("/blendedstudents")
    public Student getBlendedStudents() {
        return pc.getAllBlendedStudents();
    }

    @GetMapping("/ouasstudents")
    public Student getOuasStudents() {
        return pc.getAllOuasStudents();
    }
    //haetaan annettuun vuoteen mennessä valmistuneet opiskelijat
    @GetMapping("/graduatedstudents/{year}")
    public List<Student> getGraduatedStudents(@PathVariable int year) {
        return pc.getGraduatedStudents(year);
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

    @PostMapping("/dl_student") // JOS ON AIKAA NIIN KATSO LUENTO 25 kohta 29 ja tee tämä Response entitytllä
    public String addDayLearningStudent(@RequestBody DayLearningStudent student) {
        if (pc.addDayLearningStudent(student) == true) {
            return "Day learning student added";
        }
        return "Something went wrong!";// katso myös luento 25 kohta 30.15, virheen hallinnasta

    }

    @PostMapping("/bl_student") // JOS ON AIKAA NIIN KATSO LUENTO 25 kohta 29 ja tee tämä Response entitytllä
    public String addBlendedLearningStudent(@RequestBody BlendedLearningStudent student) {

        if (pc.addBlendedLearningStudent(student) == true) {
            return "Blended learning student added";
        }
        return "Something went wrong!";
    }

    @PostMapping("/oa_student") // JOS ON AIKAA NIIN KATSO LUENTO 25 kohta 29 ja tee tämä Response entitytllä
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

}
