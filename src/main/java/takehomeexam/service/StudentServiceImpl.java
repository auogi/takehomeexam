package takehomeexam.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takehomeexam.exception.CustomDBException;
import takehomeexam.model.Student;
import takehomeexam.repository.StudentRepository;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.isPresent() ? student.get() : null;
    }
    @Override
    public List<Student> findStudentsByClassroom(String classroom) {
        return studentRepository.findAllByClassRoom(classroom);
    }

    @Override
    public Student addStudent(Student student) {
        try{
            return studentRepository.save(student);
        }catch(Exception e){
            throw new CustomDBException(e.getMessage());
        }
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findById(student.getId());

        if(existingStudent.isPresent()){
            try{
                return studentRepository.saveAndFlush(existingStudent.get());
            }catch(Exception e){
                throw new CustomDBException(e.getMessage());
            }
        }else{
            throw new CustomDBException("Student ID Does Not Exist");
        }
    }

    @Override
    public void deleteStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findById(student.getId());
        if(existingStudent.isPresent()){
            studentRepository.deleteById(existingStudent.get().getId());
        }else{
            throw new CustomDBException("Student ID Does Not Exist");
        }
    }
}
