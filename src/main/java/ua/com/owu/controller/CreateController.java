package ua.com.owu.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.owu.entity.*;
import ua.com.owu.entity.enums.Social;
import ua.com.owu.service.*;
import ua.com.owu.service.util.Helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class CreateController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/createCourse")
    public String createCourse(
            @RequestParam String courseTitle,
            @RequestParam Integer fullPrice
    ) {
        Course course = Course.builder().courseTitle(courseTitle)
                .fullPrice(fullPrice)
                .build();

        courseService.save(course);
        return "redirect:/adminPage";
    }

    @PostMapping("/createClient")
    public String createClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String phoneNumber,
            @RequestParam String email,
            @RequestParam String city,
            @RequestParam String commentAboutClient,
            @RequestParam String tagsAboutClient,
            @RequestParam String recommendation
    ) {
        System.out.println(recommendation);

        Client client = Client.builder()
                .id(new ObjectId())
                .name(name)
                .surname(surname)
                .phoneNumber(new Helper().phoneFormater(phoneNumber))
                .email(email)
                .city(city)
                .tagsAboutClient(new Helper().tagsFormater(tagsAboutClient))
                .commentsAboutClient(new ArrayList<>())
                .build();

        if (!recommendation.equals("empty")) {
            client.builder().recomendation(clientService.findOne(recommendation));
        }
        Comment comment = Comment.builder()
                .id(new ObjectId())
                .text(commentAboutClient)
                .client(client)
                .build();

        client.getCommentsAboutClient().add(comment);

        clientService.save(client);
        commentService.save(comment);

        return "redirect:/adminPage";
    }


    @PostMapping("/createApplication")
    public String createApplication(
            @RequestParam String appReciveDate,
            @RequestParam String source,
            @RequestParam String commnetFromClient,
            @RequestParam String commentFromManager,
            @RequestParam String tagsAboutApplication,
            @RequestParam String futureCourse,
            @RequestParam String appCloseDate,
            @RequestParam String client,
            @RequestParam String course,
            @RequestParam Integer discount
    ) throws ParseException {

        Course crs = courseService.findOne(course);
        Application application
                = Application.builder()
                .appReciveDate(new Helper().dateFormater(appReciveDate))
                .source(Social.valueOf(source))
                .commnetFromClient(commnetFromClient)
                .commentFromManager(commentFromManager)
                .tagsAboutApplication(new Helper().tagsFormater(tagsAboutApplication))
                .futureCourse(futureCourse)
                .appCloseDate(appCloseDate.substring(0, 25))
                .client(clientService.findOne(client))
                .course(crs)
                .discount(discount)
                .paid(0.0)
                .priceWithDiscount(new Helper().priceCounter(crs.getFullPrice(), discount))
                .build();
        application.setLeftToPay(application.getPriceWithDiscount() - application.getPaid());

        applicationService.save(application);
        return "redirect:/adminPage";
    }


    @PostMapping("/createGroup")
    public String createGroup(@RequestParam String course,
                              @RequestParam String groupIdentifier,
                              @RequestParam String startDate,
                              @RequestParam String room) throws ParseException {

        Course crs = courseService.findOne(course);
        Group group = Group.builder()
                .groupIdentifier(groupIdentifier)
                .startDate(new Helper().dateFormater(startDate))
                .course(crs)
                .room(room)
                .build();

        groupService.save(group);
        return "redirect:/adminPage";
    }


    @PostMapping("/fillGroup")
    public String fillGroup(@RequestParam String group, @RequestParam Set<String> clients) {
        Group grp = groupService.findOne(group);
        Set<Client> clientSet = clientService.findAll(clients);
        clientSet.forEach(client -> client.getGroups().add(grp));
        grp.setClients(clientSet);
        clientService.save(clientSet);
        groupService.save(grp);
        return "redirect:/adminPage";
    }


    @PostMapping("/saveApplicationWithNonExistClient")
    public String saveApplicationWithNonExistClient(
            @RequestParam String clientName,
            @RequestParam String clientSurname,
            @RequestParam String clientPhone,
            @RequestParam String clientEmail,
            @RequestParam String clientCity,
            @RequestParam String clientOurComment,
            @RequestParam String clientTagsAboutClient,
            @RequestParam String clientRecomendation,

            @RequestParam String appDateRecive,
            @RequestParam String appSource,
            @RequestParam String appCommentFromClient,
            @RequestParam String appOurComment,
            @RequestParam String appTags,
            @RequestParam String appFutureCourse,
            @RequestParam Integer appDiscount,
            @RequestParam String appCloseDate,


            @RequestParam String courseSelect,

            @RequestParam String groupSelect


    ) throws ParseException {

        Helper helper = new Helper();

        Client client = Client.builder()
                .name(clientName)
                .surname(clientSurname)
                .phoneNumber(clientPhone)
                .email(clientEmail)
                .city(clientCity)
                .tagsAboutClient(helper.tagsFormater(clientTagsAboutClient))
                .build();
        if (!clientRecomendation.equals("empty")) {
            client.builder().recomendation(clientService.findOne(clientRecomendation));
        }
        Comment comment = Comment.builder()
                .text(clientOurComment)
                .client(client)
                .build();
        client.getCommentsAboutClient().add(comment);
        clientService.save(client);
        commentService.save(comment);
        System.out.println("Client now is - " + client);


        Course course = courseService.findOne(courseSelect);
        System.out.println(appDiscount);
        if (appDiscount == 0 || appDiscount == null) appDiscount = 0;
        System.out.println(appDiscount);

        Application application = Application.builder()
                .appReciveDate(helper.dateFormater(appDateRecive))
                .source(Social.valueOf(appSource))
                .commnetFromClient(appCommentFromClient)
                .commentFromManager(appOurComment)
                .tagsAboutApplication(helper.tagsFormater(appTags))
                .futureCourse(!appFutureCourse.equals("empty") ? appFutureCourse : "n/a")
                .discount(appDiscount)
                .priceWithDiscount(helper.priceCounter(course.getFullPrice(), appDiscount))
                .client(client)
                .course(course)
                .appCloseDate(appCloseDate.substring(0, 25))

                .build();

        applicationService.save(application);

        Group group = groupService.findOne(groupSelect);
        group.getClients().add(client);
        groupService.save(group);

        return "redirect:/";
    }

    @PostMapping("/addCommentAboutClient-{clientId}")
    public String addCommentAboutClient(@RequestParam String text,
                                        @PathVariable String clientId) {
        Client client = clientService.findOne(clientId);
        Comment comment = Comment.builder()
                .id(new ObjectId())
                .client(client)
                .text(text)
                .build();
        client.getCommentsAboutClient().add(comment);

        commentService.save(comment);
        clientService.save(client);

        return "redirect:/client/" + clientId;
    }
}
