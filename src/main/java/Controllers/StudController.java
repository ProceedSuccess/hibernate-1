package Controllers;
import Entities.Stud;
import Service.StudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudController {

    private StudServiceImpl studServiceImpl;
    @Autowired
    public void setStudServiceImpl(StudServiceImpl studeServiceImpl) {
        this.studServiceImpl = studServiceImpl;
    }
    @RequestMapping("/showStudent")
    public String showNames(Model uiModel, @ModelAttribute("studentList") List<Stud> studentList) {
        studentList = studServiceImpl.getAll();
        uiModel.addAttribute("students", studentList);
        return "students";
    }
    @RequestMapping(path = "/showStudent", method = RequestMethod.POST)
    @ResponseBody
    public List<Stud> findBetweenScore(Model uiModel, @ModelAttribute("student")int min, int max) {
        List<Stud> result = new ArrayList<Stud>();
        for (Stud stud:studServiceImpl.getAll()) {
            if (stud.getId() > min && stud.getId() < max) result.add(stud);
        }
        return result;
    }
}
