package ua.com.owu.controller;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.entity.*;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    private PaymentService paymentService;

    @Autowired
    private FakeUserService fakeUserService;

    @Autowired
    private FakeAccountService fakeAccountService;

    @Autowired
    private SocialService socialService;

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

        client.getCommentsAboutClient().add(commentAboutClient);

        clientService.save(client);

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
        Social social = socialService.find(source);

        Application application
                = Application.builder()
                .id(new ObjectId())
                .appReciveDate(new Helper().dateFormater(appReciveDate))
                .source(social)
                .commnetFromClient(commnetFromClient)
                .commentFromManager(commentFromManager)
                .tagsAboutApplication(new Helper().tagsFormater(tagsAboutApplication))
                .futureCourse(futureCourse)
                .appCloseDate(appCloseDate.substring(0, 25))
                .discount(discount)
                .priceWithDiscount(new Helper().priceCounter(crs.getFullPrice(), discount))
                .leftToPay(new Helper().priceCounter(crs.getFullPrice(), discount))
                .paid(0.0)
                .client(clientObj)
                .course(crs)
                .payments(new ArrayList<>())
                .build();

//        if (clientObj.getApplications() == null){
//            ArrayList<Application> applications = new ArrayList<>();
//            applications.add(application);
//            clientObj.setApplications(applications);
//        }else {
//            clientObj.getApplications().add(application);
//        }

        clientObj.getApplications().add(application);
        social.getApplications().add(application);

        socialService.save(social);
        applicationService.save(application);
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
                .clients(new HashSet<>())
                .build();

        groupService.save(group);
        return "redirect:/adminPage";
    }


    @PostMapping("/fillGroup")
    public void fillGroup(@RequestParam String group,
                            @RequestParam Set<String> clients,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        Group grp = groupService.findOne(group);
        Set<Client> clientSet = clientService.findAll(clients);
        clientSet.forEach(client -> client.getGroups().add(grp));
        grp.getClients().addAll(clientSet);
        clientService.save(clientSet);
        groupService.save(grp);
        response.sendRedirect(request.getHeader("referer"));
    }

    @PostMapping("/deleteFromGroup")
    public void deleteFromGroup(@RequestParam String groupId,
                                @RequestParam String clientId,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        Group group = groupService.findOne(groupId);
        Client client = clientService.findOne(clientId);

        client.getGroups().remove(group);
        group.getClients().remove(client);

        groupService.save(group);
        clientService.save(client);

        response.sendRedirect(request.getHeader("referer"));
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

        client.getCommentsAboutClient().add(clientOurComment);
        System.out.println("Client now is - " + client);

        Course course = courseService.findOne(courseSelect);
        System.out.println(appDiscount);
        if (appDiscount == 0 || appDiscount == null) appDiscount = 0;
        System.out.println(appDiscount);

        Social social = socialService.find(appSource);

        Application application = Application.builder()
                .appReciveDate(helper.dateFormater(appDateRecive))
                .source(social)
                .commnetFromClient(appCommentFromClient)
                .commentFromManager(appOurComment)
                .tagsAboutApplication(helper.tagsFormater(appTags))
                .futureCourse(!appFutureCourse.equals("empty") ? appFutureCourse : "n/a")
                .client(client)
                .course(course)
                .appCloseDate(appCloseDate.substring(0, 25))
                .discount(appDiscount)
                .priceWithDiscount(new Helper().priceCounter(course.getFullPrice(), appDiscount))
                .paid(0.0)
                .leftToPay(new Helper().priceCounter(course.getFullPrice(), appDiscount))
                .payments(new ArrayList<>())
                .build();

        Group group = groupService.findOne(groupSelect);
        group.getClients().add(client);

        client.getApplications().add(application);
        social.getApplications().add(application);

        socialService.save(social);
        groupService.save(group);
        clientService.save(client);
        applicationService.save(application);

        return "redirect:/";
    }

    @PostMapping("/createFakeUser")
    public String createFakeAccount(@RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam String phone,
                                    @RequestParam String email,
                                    @RequestParam String userComment,
                                    @RequestParam MultipartFile image) throws ParseException {
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

        fakeUserService.save(fakeUser);
        return "redirect:/showFakeUsers";
    }

    @PostMapping("/createFakeAccount")
    public String createSite(@RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String url,
                             @RequestParam String registrationDate,
                             @RequestParam String lastVisitDate,
                             @RequestParam String fakeUserId,
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

        FakeUser fakeUser = fakeUserService.findById(fakeUserId);
        fakeUser.getFakeAccounts().add(fakeAccount);
        fakeAccount.setFakeUser(fakeUser);

        fakeUserService.save(fakeUser);
        fakeAccountService.save(fakeAccount);
        return "redirect:/fakeUser/"+fakeUser.getId().toString();
    }

    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestParam String accountId) throws IOException {
        FakeAccount fakeAccount = fakeAccountService.findById(accountId);
        FakeUser fakeUser = fakeAccount.getFakeUser();
        for (FakeAccount account : fakeUser.getFakeAccounts()) {
            if (account == fakeAccount) {
                fakeUser.getFakeAccounts().remove(account);
                break;
            }
        }
        fakeAccountService.delete(fakeAccount.getId().toString());
        fakeUserService.save(fakeUser);

        return "redirect:/fakeUser/"+fakeUser.getId().toString();
    }

    @PostMapping("/createSource")
    public String createSource(@RequestParam String name){
        Social social = new Social();
        social.setName(name);
        socialService.save(social);
        return "redirect:/socials";
    }

//    @GetMapping("/deleteSource/{sourceId}")
//    public String deleteSource(@PathVariable String sourceId){
//        Social social = socialService.find(sourceId);
//        List<Application> applications = social.getApplications();
//        for (Application application : applications) {
//            application.setSource(null);
//        }
//        socialService.delete(social);
//        applicationService.save(applications);
//        return "redirect:/socials";
//    }

    @PostMapping("/createPayment")
    public String createPayment(@RequestParam Double amount,
                                @RequestParam String dateOfPayment,
                                @RequestParam String appId) throws ParseException {
        Application one = applicationService.findOne(appId);
        Payment payment = Payment.builder()
                .id(new ObjectId())
                .amount(amount)
                .dateOfPayment(new Helper().dateFormater(dateOfPayment))
                .application(one)
                .build();
        one.getPayments().add(payment);

        one.setPaid(one.getPaid() + payment.getAmount());
        one.setLeftToPay(one.getLeftToPay() - payment.getAmount());

        paymentService.save(payment);
        applicationService.save(one);

        return "redirect:/payments/"+appId;
    }

}
