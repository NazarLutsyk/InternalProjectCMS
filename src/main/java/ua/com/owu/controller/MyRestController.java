package ua.com.owu.controller;

import com.sun.scenario.effect.Merge;
import org.joda.time.LocalDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.dto.ClientDTO;
import ua.com.owu.entity.*;
import ua.com.owu.entity.seo.FakeAccount;
import ua.com.owu.entity.seo.FakeUser;
import ua.com.owu.service.*;
import ua.com.owu.service.util.ClientDTOAdapter;

import java.util.Arrays;
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
    @Autowired
    private SocialService socialService;
    @Autowired
    private FakeUserService fakeUserService;
    @Autowired
    private FakeAccountService fakeAccountService;

    @PostMapping("/liveEditCourse")
    public void liveEditCourse(@RequestBody Course course) {
        System.out.println(course);
        courseService.save(course);
    }

    @PostMapping("/liveEditClient")
    public void liveEditClient(@RequestBody Client client){
        System.out.println(client);
        Client oldClient = clientService.findOne(client.getId().toString());
        BeanUtils.copyProperties(client,oldClient,
                "commentsAboutClient", "tagsAboutClient", "recomendation", "groups", "applications");
        clientService.save(oldClient);
    }

    @PostMapping(value = "/liveEditGroup")
    public void liveEditGroup(@RequestBody Group group) {
        System.out.println(group);
        Group oldGroup = groupService.findOne(group.getId().toString());
        BeanUtils.copyProperties(group, oldGroup, "clients", "course");
        groupService.save(oldGroup);
    }

    @PostMapping("/liveEditApplication")
    public void liveEditApplication(@RequestBody Application app){
        System.out.println(app);
        Application oldApp = applicationService.findOne(app.getId().toString());
        BeanUtils.copyProperties(app, oldApp,
                "tagsAboutApplication","client","course","payments","checked","source","discount",
                "priceWithDiscount","appCloseDate","paid","leftToPay");
        applicationService.save(oldApp);
    }

    @PostMapping(value = "/liveEditFakeUser")
    public void liveEditFakeUser(@RequestBody FakeUser fakeUser) {
        System.out.println(fakeUser);
        FakeUser oldFakeUser = fakeUserService.findById(fakeUser.getId().toString());
        BeanUtils.copyProperties(fakeUser, oldFakeUser, "images", "fakeUserComments", "fakeAccounts");
        fakeUserService.save(oldFakeUser);
    }

    @PostMapping(value = "/liveEditFakeAccount")
    public void liveEditFakeAccount(@RequestBody FakeAccount fakeAccount) {
        System.out.println(fakeAccount);
        FakeAccount oldFakeAccount = fakeAccountService.findById(fakeAccount.getId().toString());
        BeanUtils.copyProperties(fakeAccount, oldFakeAccount, "fakeUser", "fakeAccountComments");
        fakeAccountService.save(oldFakeAccount);
    }

    @PostMapping("/liveEditSocial")
    public void liveEditSocial(@RequestBody Social social){
        System.out.println(social);
        Social oldSocial = socialService.find(social.getId().toString());
        BeanUtils.copyProperties(social,oldSocial,"applications");
        socialService.save(social);
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
        List<Social> socials = null;
        if (params.get("socials").equals("") || params.get("socials").split(",").length == 0)
            socials = socialService.findAll();
        else
            socials = socialService.findAllByIds(Arrays.asList(params.get("socials").split(",")));

        List<String> statistic = applicationService.getSocialStatisticByPeriod(startDate, endDate, socials);
        return statistic;
    }
}
