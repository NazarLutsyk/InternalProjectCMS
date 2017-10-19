package ua.com.owu.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.entity.*;
import ua.com.owu.entity.enums.Social;
import ua.com.owu.entity.seo.FakeAccount;
import ua.com.owu.entity.seo.FakeUser;
import ua.com.owu.service.*;
import ua.com.owu.service.util.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;

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

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private FakeUserService fakeUserService;

    @Autowired
    private FakeAccountService fakeAccountService;

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
        Client clientObj = clientService.findOne(client);

        Application application
                = Application.builder()
                .id(new ObjectId())
                .appReciveDate(new Helper().dateFormater(appReciveDate))
                .source(Social.valueOf(source))
                .commnetFromClient(commnetFromClient)
                .commentFromManager(commentFromManager)
                .tagsAboutApplication(new Helper().tagsFormater(tagsAboutApplication))
                .futureCourse(futureCourse)
                .appCloseDate(appCloseDate.substring(0, 25))
                .client(clientObj)
                .course(crs)
                .build();
        Payment payment = Payment.builder()
                .id(new ObjectId())
                .application(application)
                .discount(discount)
                .priceWithDiscount(new Helper().priceCounter(crs.getFullPrice(), discount))
                .paid(0.0)
                .leftToPay(new Helper().priceCounter(crs.getFullPrice(), discount))
                .build();
        application.setPayment(payment);

//        if (clientObj.getApplications() == null){
//            ArrayList<Application> applications = new ArrayList<>();
//            applications.add(application);
//            clientObj.setApplications(applications);
//        }else {
//            clientObj.getApplications().add(application);
//        }

        clientObj.getApplications().add(application);
        applicationService.save(application);
        paymentService.save(payment);
        clientService.save(clientObj);
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
                .id(new ObjectId())
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
                .id(new ObjectId())
                .text(clientOurComment)
                .client(client)
                .build();
        client.getCommentsAboutClient().add(comment);
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
                .client(client)
                .course(course)
                .appCloseDate(appCloseDate.substring(0, 25))
                .build();

        Payment payment = Payment.builder()
                .id(new ObjectId())
                .application(application)
                .discount(appDiscount)
                .priceWithDiscount(new Helper().priceCounter(course.getFullPrice(), appDiscount))
                .paid(0.0)
                .leftToPay(new Helper().priceCounter(course.getFullPrice(), appDiscount))
                .build();

        Group group = groupService.findOne(groupSelect);
        group.getClients().add(client);

        application.setPayment(payment);
        client.getApplications().add(application);

        paymentService.save(payment);
        groupService.save(group);
        clientService.save(client);
        commentService.save(comment);
        applicationService.save(application);

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

    @PostMapping("/createFakeUser")
    public String createFakeAccount(@RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam String phone,
                                    @RequestParam String email,
                                    @RequestParam String userComment,
                                    @RequestParam MultipartFile image,
                                    @RequestParam(required = false) List<String> fakeAccounts) throws ParseException {
        FakeUser fakeUser = FakeUser.builder()
                .id(new ObjectId())
                .name(name)
                .surname(surname)
                .phone(phone)
                .email(email)
                .fakeUserComments(new ArrayList<>())
                .images(new ArrayList<>())
                .fakeAccounts(new ArrayList<>())
                .build();
        fakeUser.getFakeUserComments().add(userComment);

        if (new Helper().checkImageExt(image)) {
            String realPath = System.getProperty("user.home")
                    + File.separator + "UniversityCRMImages" + File.separator
                    + "FakeAccountsImages" + File.separator;
            String fileName = fakeUser.getName()
                    + fakeUser.getSurname()
                    + UUID.randomUUID()
                    + new Helper().getFileExt(image);
            fakeUser.getImages().add("fakeAccountImage/" + fileName);
            try {
                File file = new File(realPath);
                if (!file.exists())
                    file.mkdirs();
                image.transferTo(new File(realPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fakeAccounts != null) {
            ArrayList<FakeAccount> tempAccounts = new ArrayList<>();
            for (String fakeAccountId : fakeAccounts) {
                FakeAccount fakeAccount = fakeAccountService.findById(fakeAccountId);
                fakeAccount.setFakeUser(fakeUser);
                fakeUser.getFakeAccounts().add(fakeAccount);
                tempAccounts.add(fakeAccount);
            }
            fakeAccountService.save(tempAccounts);
        }
        fakeUserService.save(fakeUser);
        return "redirect:/showFakeUsers";
    }

    @PostMapping("/createFakeAccount")
    public String createSite(@RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String url,
                             @RequestParam String registrationDate,
                             @RequestParam String lastVisitDate,
                             @RequestParam(required = false, defaultValue = "") String fakeUserId,
                             @RequestParam String accComment) throws URISyntaxException, ParseException {
        FakeAccount fakeAccount = FakeAccount.builder()
                .id(new ObjectId())
                .login(login)
                .password(password)
                .siteUri(new URI(url))
                .fakeAccountComments(new ArrayList<>())
                .registrationDate(new Helper().dateFormaterWithoutTime(registrationDate))
                .lastVisitDate(new Helper().dateFormaterWithoutTime(lastVisitDate))
                .build();
        fakeAccount.getFakeAccountComments().add(accComment);

        if (!fakeUserId.equals("")) {
            FakeUser fakeUser = fakeUserService.findById(fakeUserId);
            fakeUser.getFakeAccounts().add(fakeAccount);
            fakeAccount.setFakeUser(fakeUser);
            fakeUserService.save(fakeUser);
        }
        fakeAccountService.save(fakeAccount);
        return "redirect:/showFakeAccounts";
    }

    @PostMapping("/disconnectAccount")
    public void disconnectAccount(@RequestParam String disconnectAccountId,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        FakeAccount fakeAccount = fakeAccountService.findById(disconnectAccountId);
        FakeUser fakeUser = fakeAccount.getFakeUser();
        fakeAccount.setFakeUser(null);
        for (FakeAccount account : fakeUser.getFakeAccounts()) {
            if (account == fakeAccount){
                fakeUser.getFakeAccounts().remove(account);
                break;
            }
        }
        fakeAccountService.save(fakeAccount);
        fakeUserService.save(fakeUser);

        response.sendRedirect(request.getHeader("referer"));

    }

    @PostMapping("/connectAccountsToUser")
    public String connectAccountsToUser(@RequestParam String userId,
                                        @RequestParam Set<String> freeAccounts) {
        FakeUser fakeUser = fakeUserService.findById(userId);
        List<FakeAccount> freeFakeAccounts = fakeAccountService.findAllByIds(freeAccounts);

        for (FakeAccount freeFakeAccount : freeFakeAccounts) {
            if (freeFakeAccount.getFakeUser() == null) {
                freeFakeAccount.setFakeUser(fakeUser);
                fakeUser.getFakeAccounts().add(freeFakeAccount);
            }
        }
        fakeAccountService.save(freeFakeAccounts);
        fakeUserService.save(fakeUser);
        return "redirect:/fakeUser/"+userId;
    }


}
