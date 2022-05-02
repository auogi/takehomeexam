package takehomeexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import takehomeexam.model.Student;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long > {
    List<Student> findAllByClassRoom(String classRoom);
}