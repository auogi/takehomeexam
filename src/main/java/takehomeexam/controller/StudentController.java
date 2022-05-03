package takehomeexam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import takehomeexam.model.Student;
import takehomeexam.service.StudentServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentServiceImpl studentServiceImpl;

    @Autowired
    public StudentController(StudentServiceImpl studentServiceImpl){
        this.studentServiceImpl = studentServiceImpl;
    }


    @PostMapping(path = "student",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudent(@RequestBody @Valid Student newStudent) {
        LOGGER.info("Initiate add new student");
        Student student = studentServiceImpl.addStudent(newStudent);

        LOGGER.info("Success Add new student {}", student);
        return new ResponseEntity<>("New Student Succesfully Added", HttpStatus.CREATED);
    }

    @PutMapping(path = "student",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStudent(@RequestBody Student existingStudent) {
        LOGGER.info("Initiate update existing student");
        Student student = studentServiceImpl.updateStudent(existingStudent);

        LOGGER.info("Success Update existing student {}", student);
        return new ResponseEntity<>("Student Succesfully Updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "student",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteStudent(@RequestBody Student existingStudent) {
        LOGGER.info("Initiate delete existing student");

        studentServiceImpl.deleteStudent(existingStudent);
        LOGGER.info("Success Delete existing student {}", existingStudent.getId());
        return new ResponseEntity<>("Student Succesfully Removed", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/fetchStudents")
    public @ResponseBody List<Student> fetchStudents(
            @RequestParam("id") Optional<Long> id,
            @RequestParam("classroom") Optional<String> classroom
    ) {
        List<Student> students = new ArrayList<>();

        if(id.isPresent()){
            LOGGER.info("Initiate find existing student by Id");
            Student student = studentServiceImpl.findStudentById(id.get());
            students.add(student);
        }else if(classroom.isPresent()){
            LOGGER.info("Initiate find existing students by Class");
            students.addAll(studentServiceImpl.findStudentsByClassroom(classroom.get()));
        }

        LOGGER.info("Student(s) founds {}", students.size());

        return students;
    }

}