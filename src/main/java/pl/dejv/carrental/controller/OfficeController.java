package pl.dejv.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dejv.carrental.entity.Office;
import pl.dejv.carrental.repository.OfficeRepository;

import java.util.List;

@Controller
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeRepository officeRepository;

    @GetMapping("/add")
    public String addOffice(Model m) {
        m.addAttribute("newOffice", new Office());
        return "addOffice";
    }

    @PostMapping("/add")
    public String addOffice(Office office) {
        officeRepository.save(office);
        return "home";
    }

    @GetMapping("/list")
    public String officeList() {
        return "officeList";
    }

    @ModelAttribute("allOffices")
    public List<Office> allOffices() {
        return this.officeRepository.findAll();
    }
}
