package ru.hogwarts.schoolsix.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoolsix.Model.Faculty;
import ru.hogwarts.schoolsix.Model.ListOfStudentsLimit;
import ru.hogwarts.schoolsix.Model.Student;
import ru.hogwarts.schoolsix.Repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private Integer count = 0;

    private Object flag = new Object();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.debug("Вызван метод addStudent (student = {})", student);
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.debug("Вызван метод findStudent (id = {})", id);
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent(Student student) {
        logger.debug("Вызван метод editStudent (student = {})", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Вызван метод deleteStudent (id = {})", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> coincidencesStudentsByAge(int age) {
        logger.debug("Вызван метод coincidencesStudentsByAge (age = {})", age);
        ArrayList<Student> resultStudent = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                resultStudent.add(student);
            }
        }
        return resultStudent;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("Вызван метод findByAgeBetween (min = {}, max = {})", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public String listOfFacultiesOfTheStudent(long id) {
        logger.debug("Вызван метод listOfFacultiesOfTheStudent (id = {})", id);
        Student student = findStudent(id);
        Faculty faculty = student.getFaculties();
        return faculty.getName();
    }

    public int getAllStudentsNumber() {
        logger.debug("Вызван метод getAllStudentsNumber");
        return studentRepository.getAllStudentsNumber();
    }

    public double getAverageAge() {
        logger.debug("Вызван метод getAverageAge");
        return studentRepository.getAverageAge();
    }

    public List<ListOfStudentsLimit> getLastStudentsById() {
        logger.debug("Вызван метод getLastStudentsById");
        return studentRepository.getLastStudentsById();
    }

    public List<Student> getStudentsByName(String name) {
        logger.debug("Вызван метод getStudentsByName (name = {})", name);
        return studentRepository.getStudentsByName(name);
    }

    public Collection<String> getFilteredByName() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(student -> student.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }

    public Double getAllStudentsAvgAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    public void listOfAllStudentsInParallelStream() {
        List<String> namesStudents = studentRepository.findAll().stream()
                .map(user -> user.getName())
                .collect(Collectors.toList());

        printToConsoleInParallelStream(namesStudents.get(0));
        printToConsoleInParallelStream(namesStudents.get(1));

        new Thread(() -> {
            printToConsoleInParallelStream(namesStudents.get(2));
            printToConsoleInParallelStream(namesStudents.get(3));
        }).start();

        new Thread(() -> {
            printToConsoleInParallelStream(namesStudents.get(4));
            printToConsoleInParallelStream(namesStudents.get(5));
        }).start();
    }

    private void printToConsoleInParallelStream(String names) {
        System.out.println("Студент с именем " + names);
    }

    public void listOfAllStudentsViaSynchronization() {
        List<String> namesStudents = studentRepository.findAll().stream()
                .map(user -> user.getName())
                .collect(Collectors.toList());

        printToConsoleViaSynchronization(namesStudents.get(0));
        printToConsoleViaSynchronization(namesStudents.get(1));

        new Thread(() -> {
            printToConsoleViaSynchronization(namesStudents.get(2));
            printToConsoleViaSynchronization(namesStudents.get(3));
        }).start();

        new Thread(() -> {
            printToConsoleViaSynchronization(namesStudents.get(4));
            printToConsoleViaSynchronization(namesStudents.get(5));
        }).start();
    }

    private void printToConsoleViaSynchronization(String names) {
        synchronized (flag) {
            System.out.println("Студент с именем " + names + " Счетчик " + count);
            count++;
        }
    }
}
