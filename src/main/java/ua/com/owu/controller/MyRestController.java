package ua.com.owu.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.dto.ClientDTO;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Course;
import ua.com.owu.entity.Group;
import ua.com.owu.service.ApplicationService;
import ua.com.owu.service.ClientService;
import ua.com.owu.service.CourseService;
import ua.com.owu.service.GroupService;
import ua.com.owu.service.util.ClientDTOAdapter;

import java.util.List;
import java.util.Map;

@RestController
public class MyRestController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ClientDTOAdapter clientDTOAdapter;


    @PostMapping("/editNow")
    public void editNow(@RequestBody Client client) {

        System.out.println("client from request \n" + client.getTagsAboutClient());
        client.getTagsAboutClient().forEach(System.out::println);
        clientService.save(client);

    }

    @PostMapping("/liveEditCourse")
    public void liveEditCourse(@RequestBody Course course) {
        System.out.println(course);
        courseService.save(course);
        System.out.println("doneeee");
    }

    @PostMapping(value = "/findClientsWitchNotFromGroup")
    public List<ClientDTO> findClientsWitchNotFromGroup(@RequestBody String groupId) {
        List<ClientDTO> clients = clientDTOAdapter.convertToListOfClientDTO(
                clientService.findClientsWitchNotFromGroupAndWithApp(groupId)
        );
        return clients;
    }

    @PostMapping("/changeClientGroup")
    public void changeClientGroup(@RequestBody Map<String, String> params) {
        String newGroupId = params.get("newGroupId");
        String oldGroupId = params.get("oldGroupId");
        String clientId = params.get("clientId");

        Client client = clientService.findOne(clientId);
        Group newGroup = groupService.findOne(newGroupId);
        Group oldGroup = groupService.findOne(oldGroupId);

        client.getGroups().remove(oldGroup);
        client.getGroups().add(newGroup);
        oldGroup.getClients().remove(client);
        newGroup.getClients().add(client);

        clientService.save(client);
        groupService.save(oldGroup);
        groupService.save(newGroup);
    }

    @PostMapping("/addGroupToClient")
    public void addGroupToClient(@RequestBody Map<String, String> params) {
        String groupId = params.get("groupId");
        String clientId = params.get("clientId");

        Group group = groupService.findOne(groupId);
        Client client = clientService.findOne(clientId);

        client.getGroups().add(group);
        group.getClients().add(client);

        groupService.save(group);
        clientService.save(client);
    }

    @PostMapping("/reverseAppIsCheck")
    public void reverseAppIsCheck(@RequestBody String appId) {
        applicationService.reverseChecker(appId);
    }

    @PostMapping("/getSocialStatistic")
    public List<String> getSocialStatisticGroupBySocial(@RequestBody Map<String, String> params) {
        LocalDate startDate;
        LocalDate endDate;
        if (!(params.get("startDate").equals("") || params.get("endDate").equals(""))){
            startDate = LocalDate.parse(params.get("startDate"));
            endDate = LocalDate.parse(params.get("endDate"));
        }else {
            startDate = new LocalDate(1970,1,1);
            endDate = new LocalDate(3000,1,1);
        }
        List<String> statistic = applicationService.getSocialStatisticByPeriod(startDate, endDate);
        return statistic;
    }
}
