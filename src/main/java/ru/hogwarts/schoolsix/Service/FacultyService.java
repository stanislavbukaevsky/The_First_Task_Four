package ru.hogwarts.schoolsix.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.debug("Вызван метод addFaculty (faculty = {})", faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.debug("Вызван метод findFaculty (id = {})", id);
        return facultyRepository.findById(id).orElseThrow();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Вызван метод editFaculty (faculty = {})", faculty);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.debug("Вызван метод deleteFaculty (id = {})", id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> matchingFacultiesByColor(String color) {
        logger.debug("Вызван метод matchingFacultiesByColor (color = {})", color);
        ArrayList<Faculty> resultFaculty = new ArrayList<>();
        for (Faculty faculty : facultyRepository.findAll()) {
            if (Objects.equals(faculty.getColor(), color)) {
                resultFaculty.add(faculty);
            }
        }
        return resultFaculty;
    }

    public Collection<Faculty> findByName(String name) {
        logger.debug("Вызван метод findByName (name = {})", name);
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.debug("Вызван метод findByColor (color = {})", color);
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    public Collection<Student> listOfStudentsOfTheFaculty(long id) {
        logger.debug("Вызван метод listOfStudentsOfTheFaculty (id = {})", id);
        Faculty faculty = findFaculty(id);
        return studentRepository.findByFacultiesId(faculty.getId());
    }
}
