package Service;

import Entities.Stud;

import java.util.List;
import java.util.Optional;

public interface StudService {
    Stud addStud(Stud student);
    void delete(int id);
    Stud getById(Integer id);
    Stud setStudent(Stud stud);
    double getScore (String name);
    List<Stud> getAll();
}
