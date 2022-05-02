package takehomeexam.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import takehomeexam.model.Student;

@DataJpaTest
public class StudentRepositoryIT {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup() {
        student = Student.builder()
                .firstName("Integration")
                .lastName("DB")
                .classRoom("C1A")
                .nationality("American")
                .build();
    }

    @Test()
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        Student student1 = Student.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .classRoom("ramesh@gmail,com")
                .nationality("1231")
                .build();

        Student savedEmployee = studentRepository.save(student1);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
}