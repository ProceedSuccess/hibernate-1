package Service;

import Entities.Stud;
import Repositories.StudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudServiceImpl implements StudService {

    private StudRepository studRepository;

    @Autowired
    public void setStudRepository(StudRepository studRepository) {
        this.studRepository = studRepository;
    }


    @Override
    public Stud addStud(Stud student) {
        Stud stud = studRepository.saveAndFlush(student);
        return stud;
    }

    @Override
    public void delete(int id) {
        studRepository.deleteById(id);
    }

    @Override
    public Stud getById(Integer id) {
        return studRepository.getOne(id);
    }

    @Override
    public Stud setStudent(Stud stud) {
        return studRepository.save(stud);
    }

    @Override
    public List<Stud> getAll() {
        return studRepository.findAll();
    }
    @Override
    public double getScore(String name){
        return 0;
    }
}
