package ua.com.owu.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Group;
import ua.com.owu.entity.enums.Social;
import ua.com.owu.service.ApplicationService;
import ua.com.owu.service.ClientService;
import ua.com.owu.service.CourseService;
import ua.com.owu.service.GroupService;

import java.util.Date;
import java.util.List;

@Controller
public class RouteController {


    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private GroupService groupService;


    @GetMapping("/")
    public String starter(Model model) {

        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("applications", applicationService.findAll());

        return "index";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model) {

        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("sources", Social.values());
        return "adminPage";
    }

    @GetMapping("/showAllApplications")
    public String showAllApplications(Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("applications", applicationService.findAll());
        model.addAttribute("sources", Social.values());
        return "showAllApplicationsPage";
    }

    @GetMapping("/showAllCourses")
    public String showAllCourses(Model model) {

        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "showAllCoursesPage";
    }

    @GetMapping("/showAllGroups")
    public String showAllGroups(Model model,
                                @RequestParam(required = false, defaultValue = "") String course,
                                @RequestParam(required = false, defaultValue = "") String startDate,
                                @RequestParam(required = false, defaultValue = "") String endDate) {
        List<Group> groups;
        if (!(course.equals("") && startDate.equals("") && endDate.equals(""))) {
            LocalDate startDateOfCourse = startDate.equals("") ? null : new LocalDate(startDate);
            LocalDate endDateOfCourse = endDate.equals("") ? null : new LocalDate(endDate);
            groups = groupService.filterByCourseAndPeriod(course, startDateOfCourse, endDateOfCourse);
        }
        else
            groups = groupService.findAll();
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("groups", groups);
        return "showAllGroupsPage";
    }

    @GetMapping("/showAllClients")
    public String showAllClients(Model model) {

        System.out.println(clientService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("clients", clientService.findAll());
        return "showAllClientsPage";
    }

    @GetMapping("/showClientsFromGroup/{groupId}")
    public String showClientsFromGroup(@PathVariable String groupId, Model model) {
        model.addAttribute("clients", clientService.findAllByGroupIdentifier(groupId));
        return "showClientsFromGroup";
    }

    @GetMapping("/client/{idClient}")
    public String showClientPage(@PathVariable String idClient, Model model) {
        Client client = clientService.findOne(idClient);
        List<Application> applications = applicationService.findAllByClient(client);
        List<Group> otherGroups = groupService.findAll();
        otherGroups.removeAll(client.getGroups());
        model.addAttribute("client", client);
        model.addAttribute("applications", applications);
        model.addAttribute("otherGroups", otherGroups);
        return "showClientPage";
    }

    @GetMapping("/analitics")
    public String getAnaliticPage() {
        return "analiticPage";
    }


}