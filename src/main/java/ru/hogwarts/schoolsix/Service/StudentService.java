package ru.hogwarts.schoolsix.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolsix.Model.Faculty;
import ru.hogwarts.schoolsix.Model.ListOfStudentsLimit;
import ru.hogwarts.schoolsix.Model.Student;
import ru.hogwarts.schoolsix.Repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> coincidencesStudentsByAge(int age) {
        ArrayList<Student> resultStudent = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                resultStudent.add(student);
            }
        }
        return resultStudent;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public String listOfFacultiesOfTheStudent(long id) {
        Student student = findStudent(id);
        Faculty faculty = student.getFaculties();
        return faculty.getName();
    }

    public int getAllStudentsNumber() {
        return studentRepository.getAllStudentsNumber();
    }

    public double getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<ListOfStudentsLimit> getLastStudentsById() {
        return studentRepository.getLastStudentsById();
    }
}
