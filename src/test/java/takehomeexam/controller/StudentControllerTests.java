package takehomeexam.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import takehomeexam.model.Student;
import takehomeexam.repository.StudentRepository;
import takehomeexam.service.StudentServiceImpl;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
public class StudentControllerTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    public void initObjects() {
        this.studentServiceImpl = new StudentServiceImpl(this.studentRepository);
        this.studentController = new StudentController(this.studentServiceImpl);
    }

    @Test
    public void testAddStudent(){

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("American");

        Mockito.when(this.studentRepository.save(any(Student.class))).thenReturn(student);
        ResponseEntity<String> response = this.studentController.addStudent(student);


        Assertions.assertEquals(201,response.getStatusCodeValue());
        Assertions.assertEquals("New Student Succesfully Added",response.getBody());
    }

    @Test
    public void testUpdateStudent(){
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("American");


        Mockito.when(this.studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        Mockito.when(this.studentRepository.saveAndFlush(any(Student.class))).thenReturn(student);

        ResponseEntity<String> response = this.studentController.updateStudent(student);


        Assertions.assertEquals(202,response.getStatusCodeValue());
        Assertions.assertEquals("Student Succesfully Updated",response.getBody());
    }

    @Test
    public void testDeleteStudent(){
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("American");

        Mockito.when(this.studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        Mockito.doNothing().when(this.studentRepository).deleteById(anyLong());

        ResponseEntity<String> response = this.studentController.deleteStudent(student);

        Assertions.assertEquals(202,response.getStatusCodeValue());
        Assertions.assertEquals("Student Succesfully Removed",response.getBody());
    }
}
