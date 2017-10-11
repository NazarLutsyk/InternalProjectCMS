<#include "../templates/header.ftl">

<form action="/saveApp" method="post">

<#--client-->
    <h3>Client</h3>
    <input type="text" name="name" placeholder="client name"> <br>
    <input type="" name="surname" placeholder="client surname"> <br>
    <input type="" name="phoneNumber" placeholder="phone"> <br>
    <input type="email" name="email" placeholder="email"> <br>
    <input type="" name="city" placeholder="city"> <br>
    <input type="" name="commentAboutClient" placeholder="commentAboutClient"> <br>
    <input type="" name="tagsAboutClient" placeholder="tags About Client !!!!coma!!! separated"> <br>
    <hr>
<#--Course-->
    <h3>Course</h3>

    <input type="" name="courseTitle" placeholder="courseTitle"> <#--rework into select!-->
    <br>
    <input type="datetime-local" name="startDate">
    <br>
    <input type="number" name="fullPrice">
    <br>
    <input type="number" name="discount">
<#--Application-->
    <h3>Application</h3>
    <input type="datetime-local" name="date">
    <br>

    <input type="text" name="source" placeholder="source">
    <br>
    <input type="text" name="commentFromClient" placeholder="commnetFromClient">
    <br>
    <input type="text" name="commentFromManager" placeholder="commentFromManager">
    <br>
    <input type="text" name="tagsAboutApplication" placeholder="tagsAboutApplication">
    <br>
    <input type="" name="futureCourse" placeholder="future course">
    <br>
    <input type="submit" name="">
    <br>
    <input type="hidden" name="closeDate" id="closeDate">

</form>
<#include "../templates/spyScript.ftl">