package ru.hogwarts.schoolsix.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.schoolsix.Model.Faculty;
import ru.hogwarts.schoolsix.Model.ListOfStudentsLimit;
import ru.hogwarts.schoolsix.Model.Student;
import ru.hogwarts.schoolsix.Service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(student));
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age")
    public ResponseEntity<Collection<Student>> coincidencesStudentsByAge(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.coincidencesStudentsByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudentsByAge(@RequestParam int min, @RequestParam int max) {
        if (min < max) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/list-students")
    public ResponseEntity<String> listOfFacultiesOfTheStudent(@RequestParam Long id) {
        Student student = studentService.findStudent(id);
        Faculty faculty = student.getFaculties();
        if (student == null) {
            return ResponseEntity.ok().build();
        } else if (faculty == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(studentService.listOfFacultiesOfTheStudent(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getAllStudentsNumber() {
        return ResponseEntity.ok(studentService.getAllStudentsNumber());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/last-students")
    public ResponseEntity<List<ListOfStudentsLimit>> getLastStudentsById() {
        return ResponseEntity.ok(studentService.getLastStudentsById());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/filtered-by-name")
    public ResponseEntity<Collection<String>> getFilteredByName() {
        Collection<String> allStudentsByName = studentService.getFilteredByName();
        if (allStudentsByName.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(allStudentsByName);
    }

    @GetMapping("/average-age-students")
    public Double getAllStudentsAvgAge() {
        return studentService.getAllStudentsAvgAge();
    }

    @GetMapping("/names-students-parallel")
    public void listOfAllStudentsInParallelStream() {
        studentService.listOfAllStudentsInParallelStream();
    }

    @GetMapping("/names-students-synchroniz")
    public void listOfAllStudentsViaSynchronization() {
        studentService.listOfAllStudentsViaSynchronization();
    }
}
