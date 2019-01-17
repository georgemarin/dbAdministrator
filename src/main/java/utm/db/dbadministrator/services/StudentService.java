package utm.db.dbadministrator.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utm.db.dbadministrator.repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository StudentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        StudentRepository = studentRepository;
    }


    public StudentRepository getStudentRepository() {
        return StudentRepository;
    }
}

