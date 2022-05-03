package takehomeexam.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import takehomeexam.controller.StudentController;
import takehomeexam.model.Student;
import takehomeexam.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StudentServiceImplTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    public void initObjects() {
        this.studentServiceImpl = new StudentServiceImpl(this.studentRepository);
    }


    @Test
    public void shouldAddStudentAndReturnSuccessResponse(){

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("American");

        Mockito.when(this.studentRepository.save(any(Student.class))).thenReturn(student);

        assertDoesNotThrow(() -> {
            this.studentServiceImpl.addStudent(student);
        });

    }

    @Test
    public void shouldUpdateStudentAndReturnSuccessResponse(){
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("American");


        Mockito.when(this.studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        Mockito.when(this.studentRepository.saveAndFlush(any(Student.class))).thenReturn(student);

        assertDoesNotThrow(() -> {
            this.studentServiceImpl.updateStudent(student);
        });

    }

    @Test
    public void shouldDeleteStudentAndReturnSuccessResponse(){
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("American");

        Mockito.when(this.studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        Mockito.doNothing().when(this.studentRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> {
            this.studentServiceImpl.deleteStudent(student);
        });
    }

    @Test
    public void shouldFindStudentById(){
        Student expectedStudent = new Student();
        expectedStudent.setId(1L);
        expectedStudent.setFirstName("test");
        expectedStudent.setLastName("test");
        expectedStudent.setClassRoom("1C");
        expectedStudent.setNationality("American");

        Mockito.when(this.studentRepository.findById(anyLong())).thenReturn(Optional.of(expectedStudent));

        Student student = this.studentServiceImpl.findStudentById(1L);

        Assertions.assertEquals(student.getId(), 1L);
        Assertions.assertEquals(student.getClassRoom(), "1C");
        Assertions.assertEquals(student.getFirstName(), "test");
        Assertions.assertEquals(student.getLastName(), "test");
        Assertions.assertEquals(student.getNationality(), "American");
    }

    @Test
    public void shouldFindStudentsByClassroom(){
        List<Student> expectedStudents = new ArrayList<>();
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("test1");
        student1.setLastName("test1");
        student1.setClassRoom("1C");
        student1.setNationality("American");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("test2");
        student2.setLastName("test2");
        student2.setClassRoom("1C");
        student2.setNationality("American");

        expectedStudents.add(student1);
        expectedStudents.add(student2);

        Mockito.when(this.studentRepository.findAllByClassRoom(anyString())).thenReturn(expectedStudents);

        List<Student> students = this.studentServiceImpl.findStudentsByClassroom("1C");

        Assertions.assertEquals(2,students.size());
        Assertions.assertEquals(students.get(0).getId(), 1L);
        Assertions.assertEquals(students.get(0).getClassRoom(), "1C");
        Assertions.assertEquals(students.get(0).getFirstName(), "test1");
        Assertions.assertEquals(students.get(0).getLastName(), "test1");
        Assertions.assertEquals(students.get(0).getNationality(), "American");
        Assertions.assertEquals(students.get(1).getId(), 2L);
        Assertions.assertEquals(students.get(1).getClassRoom(), "1C");
        Assertions.assertEquals(students.get(1).getFirstName(), "test2");
        Assertions.assertEquals(students.get(1).getLastName(), "test2");
        Assertions.assertEquals(students.get(1).getNationality(), "American");
    }
}
