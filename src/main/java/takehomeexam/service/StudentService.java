package takehomeexam.service;

import takehomeexam.model.Student;

import java.util.List;

public interface StudentService {
    Student findStudentById(Long id);
    List<Student> findStudentsByClassroom(String classroom);

    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
