package takehomeexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takehomeexam.model.Student;
import takehomeexam.service.StudentServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    public StudentController(StudentServiceImpl studentServiceImpl){
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping(path = "/fetchStudents")
    public @ResponseBody List<Student> fetchStudents(
            @RequestParam("id") Optional<Long> id,
            @RequestParam("classroom") Optional<String> classroom
    ) {
        List<Student> students = new ArrayList<>();

        if(id.isPresent()){
            Student student = studentServiceImpl.findStudentById(id.get());
            students.add(student);
        }else if(classroom.isPresent()){
            students.addAll(studentServiceImpl.findStudentsByClassroom(classroom.get()));
        }

        return students;
    }

    @PostMapping(path = "student",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudent(@RequestBody Student newStudent) {
        studentServiceImpl.addStudent(newStudent);
        return new ResponseEntity<>("New Student Succesfully Added", HttpStatus.CREATED);
    }

    @PutMapping(path = "student",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStudent(@RequestBody Student existingStudent) {
        studentServiceImpl.updateStudent(existingStudent);
        return new ResponseEntity<>("Student Succesfully Updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "student",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteStudent(@RequestBody Student existingStudent) {
        studentServiceImpl.deleteStudent(existingStudent);
        return new ResponseEntity<>("Student Succesfully Removed", HttpStatus.ACCEPTED);
    }
}