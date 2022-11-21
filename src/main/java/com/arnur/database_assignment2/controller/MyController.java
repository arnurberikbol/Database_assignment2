package com.arnur.database_assignment2.controller;

import com.arnur.database_assignment2.dao.*;
import com.arnur.database_assignment2.entity.*;
import com.arnur.database_assignment2.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private DiscoverDao discoverDao;

    @Autowired
    private DiseaseDao diseaseDao;

    @Autowired
    private DiseaseTypeDao diseaseTypeDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PublicServantDao publicServantDao;

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private SpecializeDao specializeDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping("/countries")
    public String showAllCountries(Model model) {

        List<Country> allCountries = countryDao.getAllCountries();
        model.addAttribute("countries", allCountries);
        return "all-countries";
    }

    @RequestMapping("/addNewCountry")
    public String addNewCountry(Model model) {
        Country country = new Country();
        model.addAttribute("country", country);

        return "add-country";
    }

    @RequestMapping("/saveCountry")
    public String saveCountry(@ModelAttribute("country") Country country, Model model) {
        boolean noException = countryDao.saveCountry(country);

        if (!noException) {
            String exception = "Country already exists";
            model.addAttribute("exception", exception);
            return "update-country";
        }

        return "redirect:/countries";
    }

    @RequestMapping("/persistCountry")
    public String persistCountry(@ModelAttribute("country") Country country, Model model) {
        boolean noException = countryDao.persistCountry(country);

        if (!noException) {
            String exception = "Country already exists";
            model.addAttribute("exception", exception);
            return "add-country";
        }

        return "redirect:/countries";
    }

    @RequestMapping("/updateCountry/{cname}")
    public String updateCountry(@PathVariable("cname") String cname, Model model) {

        Country country = countryDao.getCountry(cname);
        model.addAttribute("country", country);

        return "update-country";
    }

    @RequestMapping("/delete/{cname}")
    public String deleteCountry(@PathVariable("cname") String cname) {

        countryDao.deleteCountry(cname);

        return "redirect:/countries";
    }

    @RequestMapping("/discovers")
    public String showAllDiscovers(Model model) {

        List<Discover> allDiscovers = discoverDao.getAllDiscovers();
        model.addAttribute("discovers", allDiscovers);
        return "all-discovers";
    }

    @RequestMapping("/addNewDiscover")
    public String addNewDiscover(Model model) {
        Discover discover= new Discover();
        List<Disease> diseases = diseaseDao.getAllDiseases();
        List<Country> countries = countryDao.getAllCountries();

        model.addAttribute("diseases", diseases);
        model.addAttribute("countries", countries);
        model.addAttribute("discover", discover);

        return "add-discover";
    }

    @RequestMapping("/saveDiscover")
    public String save(@ModelAttribute("discover") Discover discover, Model model) {
        boolean noException = discoverDao.saveDiscover(discover);

        if (!noException) {
            String exception = "Discover already exists";
            model.addAttribute("exception", exception);
            return "update-discover";
        }

        return "redirect:/discovers";
    }

    @RequestMapping("/persistDiscover")
    public String persistDiscover(@ModelAttribute("discover") Discover discover, Model model) {
        boolean noException = discoverDao.persistDiscover(discover);

        List<Country> countries = countryDao.getAllCountries();
        if (!noException) {
            String exception = "Discover already exists";
            model.addAttribute("exception", exception);
            model.addAttribute("countries", countries);
            return "add-discover";
        }

        return "redirect:/discovers";
    }

    @RequestMapping("/updateDiscover/{cname}/{disease_code}")
    public String updateDiscover(@PathVariable("cname") String cname, @PathVariable("disease_code") String disease_code, Model model) {

        Discover discover = discoverDao.getDiscover(cname, disease_code);
        model.addAttribute("discover", discover);

        return "update-discover";
    }

    @RequestMapping("/deleteDiscover/{cname}/{disease_code}")
    public String deleteDiscover(@PathVariable("cname") String cname, @PathVariable("disease_code") String disease_code) {

        discoverDao.deleteDiscover(cname, disease_code);

        return "redirect:/discovers";
    }

    @RequestMapping("/diseases")
    public String showAllDiseases(Model model) {

        List<Disease> allDiseases = diseaseDao.getAllDiseases();
        model.addAttribute("diseases", allDiseases);
        return "all-diseases";
    }

    @RequestMapping("/addNewDisease")
    public String addNewDisease(Model model) {
        Disease disease = new Disease();
        List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();

        model.addAttribute("disease", disease);
        model.addAttribute("diseaseTypes", diseaseTypes);

        return "add-disease";
    }

    @RequestMapping("/saveDisease")
    public String saveDisease(@ModelAttribute("disease") Disease disease, Model model) {
        boolean noException = diseaseDao.saveDisease(disease);

        if (!noException) {
            String exception = "Disease already exists";
            model.addAttribute("exception", exception);
            List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();
            model.addAttribute("diseaseTypes", diseaseTypes);
            return "update-disease";
        }

        return "redirect:/diseases";
    }

    @RequestMapping("/persistDisease")
    public String persistDisease(@ModelAttribute("disease") Disease disease, Model model) {
        boolean noException = diseaseDao.persistDisease(disease);

        if (!noException) {
            String exception = "Disease already exists";
            model.addAttribute("exception", exception);
            List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();
            model.addAttribute("diseaseTypes", diseaseTypes);
            return "add-disease";
        }

        return "redirect:/diseases";
    }

    @RequestMapping("/updateDisease/{disease_code}")
    public String updateDisease(@PathVariable("disease_code") String disease_code, Model model) {

        Disease disease = diseaseDao.getDisease(disease_code);
        List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();

        model.addAttribute("disease", disease);
        model.addAttribute("diseaseTypes", diseaseTypes);

        return "update-disease";
    }

    @RequestMapping("/deleteDisease/{disease_code}")
    public String deleteDisease(@PathVariable("disease_code") String disease_code) {

        diseaseDao.deleteDisease(disease_code);

        return "redirect:/diseases";
    }

    @RequestMapping("/diseaseTypes")
    public String showAllDiseaseTypes(Model model) {

        List<Diseasetype> allDiseaseTypes = diseaseTypeDao.getAllDiseaseTypes();
        model.addAttribute("diseaseTypes", allDiseaseTypes);
        return "all-diseaseTypes";
    }

    @RequestMapping("/addNewDiseaseType")
    public String addNewDiseaseType(Model model) {
        Diseasetype diseaseType = new Diseasetype();
        model.addAttribute("diseaseType", diseaseType);

        return "add-diseaseType";
    }

    @RequestMapping("/saveDiseaseType")
    public String saveDiseaseType(@ModelAttribute("diseaseType") Diseasetype diseaseType, Model model) {
        boolean noException = diseaseTypeDao.saveDiseaseType(diseaseType);

        if (!noException) {
            String exception = "DiseaseType already exists";
            model.addAttribute("exception", exception);
            return "update-diseaseType";
        }

        return "redirect:/diseaseTypes";
    }


    @RequestMapping("/persistDiseaseType")
    public String persistDiseaseType(@ModelAttribute("diseaseType") Diseasetype diseaseType, Model model) {
        boolean noException = diseaseTypeDao.persistDiseaseType(diseaseType);

        if (!noException) {
            String exception = "DiseaseType already exists";
            model.addAttribute("exception", exception);
            return "add-diseaseType";
        }

        return "redirect:/diseaseTypes";
    }

    @RequestMapping("/updateDiseaseType/{id}")
    public String updateDiseaseType(@PathVariable("id") int id, Model model) {

        Diseasetype diseaseType = diseaseTypeDao.getDiseaseType(id);
        model.addAttribute("diseaseType", diseaseType);

        return "update-diseaseType";
    }

    @RequestMapping("/deleteDiseaseType/{id}")
    public String deleteDiseaseType(@PathVariable("id") int id) {

        diseaseTypeDao.deleteDiseaseType(id);

        return "redirect:/diseaseTypes";
    }

    @RequestMapping("/doctors")
    public String showAllDoctors(Model model) {

        List<Doctor> allDoctors = doctorDao.getAllDoctors();
        model.addAttribute("doctors", allDoctors);
        return "all-doctors";
    }

    @RequestMapping("/addNewDoctor")
    public String addNewDoctor(Model model) {
        Doctor doctor = new Doctor();
        List<User> users = userDao.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("doctor", doctor);

        return "add-doctor";
    }

    @RequestMapping("/saveDoctor")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor, Model model) {

        boolean noException = doctorDao.saveDoctor(doctor);

        if (!noException) {
            String exception = "Doctor already exists";
            model.addAttribute("exception", exception);
            List<User> users = userDao.getAllUsers();

            model.addAttribute("users", users);
            return "update-doctor";
        }

        return "redirect:/doctors";
    }

    @RequestMapping("/persistDoctor")
    public String persistDoctor(@ModelAttribute("doctor") Doctor doctor, Model model) {

        boolean noException = doctorDao.persistDoctor(doctor);

        if (!noException) {
            String exception = "Doctor already exists";
            model.addAttribute("exception", exception);
            List<User> users = userDao.getAllUsers();

            model.addAttribute("users", users);
            return "add-doctor";
        }

        return "redirect:/doctors";
    }

    @RequestMapping("/updateDoctor/{email}")
    public String updateDoctor(@PathVariable("email") String email, Model model) {

        Doctor doctor = doctorDao.getDoctor(email);
        model.addAttribute("doctor", doctor);
        List<User> users = userDao.getAllUsers();

        model.addAttribute("users", users);

        return "update-doctor";
    }

    @RequestMapping("/deleteDoctor/{email}")
    public String deleteDoctor(@PathVariable("email") String email) {

        doctorDao.deleteDoctor(email);

        return "redirect:/doctors";
    }

    @RequestMapping("/publicServants")
    public String showAllPublicServants(Model model) {

        List<Publicservant> allPublicServants = publicServantDao.getAllPublicServants();
        model.addAttribute("publicServants", allPublicServants);
        return "all-publicServants";
    }

    @RequestMapping("/addNewPublicServant")
    public String addNewPublicServant(Model model) {
        Publicservant publicServant = new Publicservant();
        model.addAttribute("publicServant", publicServant);

        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);

        return "add-publicServant";
    }

    @RequestMapping("/savePublicServant")
    public String savePublicServant(@ModelAttribute("publicServant") Publicservant publicServant, Model model) {
        boolean noException = publicServantDao.savePublicServant(publicServant);

        if (!noException) {
            String exception = "Public servant already exists";
            model.addAttribute("exception", exception);
            return "update-publicServant";
        }

        return "redirect:/publicServants";
    }

    @RequestMapping("/persistPublicServant")
    public String persistPublicServant(@ModelAttribute("publicServant") Publicservant publicServant, Model model) {
        boolean noException = publicServantDao.persistPublicServant(publicServant);

        if (!noException) {
            String exception = "Public servant already exists";
            model.addAttribute("exception", exception);
            return "add-publicServant";
        }

        return "redirect:/publicServants";
    }


    @RequestMapping("/updatePublicServant/{email}")
    public String updatePublicServant(@PathVariable("email") String email, Model model) {

        Publicservant publicServant = publicServantDao.getPublicServant(email);
        model.addAttribute("publicServant", publicServant);

        return "update-publicServant";
    }

    @RequestMapping("/deletePublicServant/{email}")
    public String deletePublicServant(@PathVariable("email") String email) {

        publicServantDao.deletePublicServant(email);

        return "redirect:/publicServants";
    }

    @RequestMapping("/records")
    public String showAllRecords(Model model) {

        List<Record> allRecords = recordDao.getAllRecords();
        model.addAttribute("records", allRecords);
        return "all-records";
    }

    @RequestMapping("/addNewRecord")
    public String addNewRecord(Model model) {
        Record record = new Record();
        List<Publicservant> users = publicServantDao.getAllPublicServants();
        List<Country> countries = countryDao.getAllCountries();
        List<Disease> diseases = diseaseDao.getAllDiseases();

        model.addAttribute("users", users);
        model.addAttribute("countries", countries);
        model.addAttribute("diseases", diseases);
        model.addAttribute("record", record);

        return "add-record";
    }

    @RequestMapping("/saveRecord")
    public String saveRecord(@ModelAttribute("record") Record record, Model model) {
        boolean noException = recordDao.saveRecord(record);

        if (!noException) {
            String exception = "Record already exists";
            model.addAttribute("exception", exception);
            List<Publicservant> users = publicServantDao.getAllPublicServants();
            List<Country> countries = countryDao.getAllCountries();
            List<Disease> diseases = diseaseDao.getAllDiseases();

            model.addAttribute("users", users);
            model.addAttribute("countries", countries);
            model.addAttribute("diseases", diseases);
            return "update-record";
        }

        return "redirect:/records";
    }

    @RequestMapping("/persistRecord")
    public String persistRecord(@ModelAttribute("record") Record record, Model model) {
        boolean noException = recordDao.persistRecord(record);

        if (!noException) {
            String exception = "Record already exists";
            model.addAttribute("exception", exception);
            List<Publicservant> users = publicServantDao.getAllPublicServants();
            List<Country> countries = countryDao.getAllCountries();
            List<Disease> diseases = diseaseDao.getAllDiseases();

            model.addAttribute("users", users);
            model.addAttribute("countries", countries);
            model.addAttribute("diseases", diseases);
            return "add-record";
        }

        return "redirect:/records";
    }

    @RequestMapping("/updateRecord/{email}/{cname}/{disease_code}")
    public String updateRecord(@PathVariable("email") String email, @PathVariable("cname") String cname, @PathVariable("disease_code") String disease_code, Model model) {

        Record record = recordDao.getRecord(email, cname, disease_code);
        model.addAttribute("record", record);
        List<Publicservant> users = publicServantDao.getAllPublicServants();
        List<Country> countries = countryDao.getAllCountries();
        List<Disease> diseases = diseaseDao.getAllDiseases();

        model.addAttribute("users", users);
        model.addAttribute("countries", countries);
        model.addAttribute("diseases", diseases);

        return "update-record";
    }

    @RequestMapping("/deleteRecord/{email}/{cname}/{disease_code}\"")
    public String deleteRecord(@PathVariable("email") String email, @PathVariable("cname") String cname, @PathVariable("disease_code") String disease_code) {

        recordDao.deleteRecord(email, cname, disease_code);

        return "redirect:/records";
    }

    @RequestMapping("/specializes")
    public String showAllSpecializes(Model model) {

        List<Specialize> allSpecializes = specializeDao.getAllSpecializes();
        model.addAttribute("specializes", allSpecializes);
        return "all-specializes";
    }

    @RequestMapping("/addNewSpecialize")
    public String addNewSpecialize(Model model) {
        Specialize specialize = new Specialize();
        model.addAttribute("specialize", specialize);

        List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();
        model.addAttribute("diseaseTypes", diseaseTypes);
        List<Doctor> users = doctorDao.getAllDoctors();
        model.addAttribute("users", users);

        return "add-specialize";
    }

    @RequestMapping("/saveSpecialize")
    public String saveSpecialize(@ModelAttribute("specialize") Specialize specialize, Model model) {
        boolean noException = specializeDao.saveSpecialize(specialize);

        if (!noException) {
            String exception = "Specialize already exists";
            model.addAttribute("exception", exception);
            List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();
            model.addAttribute("diseaseTypes", diseaseTypes);
            List<Doctor> users = doctorDao.getAllDoctors();
            model.addAttribute("users", users);
            return "update-specialize";
        }

        return "redirect:/specializes";
    }

    @RequestMapping("/persistSpecialize")
    public String persistSpecialize(@ModelAttribute("specialize") Specialize specialize, Model model) {
        boolean noException = specializeDao.persistSpecialize(specialize);

        if (!noException) {
            String exception = "Specialize already exists";
            model.addAttribute("exception", exception);
            List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();
            model.addAttribute("diseaseTypes", diseaseTypes);
            List<Doctor> users = doctorDao.getAllDoctors();
            model.addAttribute("users", users);
            return "add-specialize";
        }

        return "redirect:/specializes";
    }

    @RequestMapping("/updateSpecialize/{id}/{email}")
    public String updateSpecialize(@PathVariable("id") int id, @PathVariable("email") String email, Model model) {

        Specialize specialize = specializeDao.getSpecialize(id, email);
        model.addAttribute("specialize", specialize);

        List<Diseasetype> diseaseTypes = diseaseTypeDao.getAllDiseaseTypes();
        model.addAttribute("diseaseTypes", diseaseTypes);
        List<Doctor> users = doctorDao.getAllDoctors();
        model.addAttribute("users", users);

        return "update-specialize";
    }

    @RequestMapping("/deleteSpecialize/{id}/{email}")
    public String deleteSpecialize(@PathVariable("id") int id, @PathVariable("email") String email) {

        specializeDao.deleteSpecialize(id, email);

        return "redirect:/specializes";
    }

    @RequestMapping("/users")
    public String showAllUsers(Model model) {

        List<User> allUsers = userDao.getAllUsers();
        model.addAttribute("users", allUsers);
        return "all-users";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        List<Country> countries = countryDao.getAllCountries();

        model.addAttribute("countries",countries);
        model.addAttribute("user", user);

        return "add-user";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        boolean noException = userDao.saveUser(user);

        if (!noException) {
            String exception = "User already exists";
            model.addAttribute("exception", exception);
            return "update-user";
        }

        return "redirect:/users";
    }

    @RequestMapping("/persistUser")
    public String persistUser(@ModelAttribute("user") User user, Model model) {
        boolean noException = userDao.persistUser(user);

        if (!noException) {
            String exception = "User already exists";
            model.addAttribute("exception", exception);
            return "add-user";
        }

        return "redirect:/users";
    }

    @RequestMapping("/updateUser/{email}")
    public String updateUser(@PathVariable("email") String email, Model model) {

        User user = userDao.getUser(email);
        List<Country> countries = countryDao.getAllCountries();

        model.addAttribute("countries",countries);
        model.addAttribute("user", user);

        return "update-user";
    }

    @RequestMapping("/deleteUser/{email}")
    public String deleteUser(@PathVariable("email") String email) {

        userDao.deleteUser(email);

        return "redirect:/users";
    }


}
