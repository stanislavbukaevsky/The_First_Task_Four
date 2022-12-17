package ru.hogwarts.schoolsix.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolsix.Model.Faculty;
import ru.hogwarts.schoolsix.Model.Student;
import ru.hogwarts.schoolsix.Repositories.FacultyRepository;
import ru.hogwarts.schoolsix.Repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> matchingFacultiesByColor(String color) {
        ArrayList<Faculty> resultFaculty = new ArrayList<>();
        for (Faculty faculty : facultyRepository.findAll()) {
            if (Objects.equals(faculty.getColor(), color)) {
                resultFaculty.add(faculty);
            }
        }
        return resultFaculty;
    }

    public Collection<Faculty> findByName(String name) {
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    public Collection<Student> listOfStudentsOfTheFaculty(long id) {
        Faculty faculty = findFaculty(id);
        return studentRepository.findByFacultiesId(faculty.getId());
    }
}
