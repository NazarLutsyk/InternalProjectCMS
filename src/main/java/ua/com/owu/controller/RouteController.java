package ua.com.owu.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Group;
import ua.com.owu.service.*;

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
    @Autowired
    private FakeUserService fakeUserService;
    @Autowired
    private FakeAccountService fakeAccountService;
    @Autowired
    private SocialService socialService;


    @GetMapping("/")
    public String starter(Model model) {
        return "index";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model) {

        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("sources", socialService.findAll());
        return "adminPage";
    }

    @GetMapping("/showAllApplications")
    public String showAllApplications(Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("applications", applicationService.findAll());
        model.addAttribute("sources", socialService.findAll());
        return "showAllApplicationsPage";
    }

    @GetMapping("/payments/{appId}")
    public String showAllPayments(@PathVariable String appId, Model model) {
        Application one = applicationService.findOne(appId);
        model.addAttribute("payments",one.getPayments());
        model.addAttribute("application",one);
        return "appPaymentPage";
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
        } else
            groups = groupService.findAll();
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("groups", groups);
        return "showAllGroupsPage";
    }

    @GetMapping("/showAllClients")
    public String showAllClients(Model model,
                                 @RequestParam(required = false, defaultValue = "false") boolean withoutGroups) {
        List<Client> clients;
        if (withoutGroups)
            clients = clientService.findClientsWithoutGroups();
        else
            clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "showAllClientsPage";
    }

    @GetMapping("/showClientsFromGroup/{groupId}")
    public String showClientsFromGroup(@PathVariable("groupId") String groupName, Model model) {
        Group group = groupService.findOneByGroupName(groupName);
        model.addAttribute("clients", clientService.findAllByGroupIdentifier(groupName));
        model.addAttribute("otherClients",
                clientService.findClientsWitchNotFromGroupAndWithApp(group.getId().toString()));
        model.addAttribute("group", group);
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
    public String getAnaliticPage(Model model) {
        model.addAttribute("sources",socialService.findAll());
        return "analiticPage";
    }

    @GetMapping("/showFakeUsers")
    public String getSitePage(Model model) {
        model.addAttribute("fakeUsers", fakeUserService.findAll());
        return "showAllFakeUsersPage";
    }

    @GetMapping("/fakeUser/{id}")
    public String getFakeUserPage(@PathVariable String id,Model model) {
        model.addAttribute("fakeUser",fakeUserService.findById(id));
        return "showFakeUserPage";
    }

}
