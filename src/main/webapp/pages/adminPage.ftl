<#include "templates/header.ftl">

<h2>Создание курс</h2>
<form action="/createCourse" method="post">
    <label>название курса<input type="text" name="courseTitle" placeholder="course title"></label><br>
    <label>полная стоимость<input type="number" min="0" name="fullPrice" placeholder="full price"></label><br>

    <input type="submit" name="">
</form>


<h2>создание клиента</h2>
<form action="/createClient" method="post">
    <label for="">имя<input type="text" name="name" placeholder="name"></label><br>
    <label for="">фамилия<input type="text" name="surname" placeholder="surname"></label><br>
    <label for="">телефон<input type="text" name="phoneNumber" placeholder="phoneNumber"></label><br>
    <label for="">мыло<input type="email" name="email" placeholder="email"></label><br>
    <label for="">город<input type="text" name="city" placeholder="city"></label><br>
    <label for="">наш коммент<input type="text" name="commentAboutClient" placeholder="commentAboutClient"></label><br>
    <label for="">теги<input type="text" name="tagsAboutClient" placeholder="tagsAboutClient"></label><br>
<#--<label for=""><input type="text" id="recomendation" placeholder="рекомендация"></label>-->
    <label for="">рекомендации
        <select name="recommendation" id="" class="js-example-basic-single" placeholder="рекомендации">
            <option value="empty">empty</option>
        <#list clients as client>
            <option value="${client.id}">${client.name} ${client.surname} ${client.phoneNumber}</option>
        </#list>
        </select>
    </label>

    <input type="submit" name="" placeholder="">

</form>

<h2>создание заявки</h2>
<form action="/createApplication" method="post">

    <label for="">время подачи заявки<input type="datetime-local" name="appReciveDate"
                                            placeholder="appReciveDate"></label><br>
    <label for="">откуда узнал
        <select name="source">
        <#list sources as source>
            <option value="${source}">
            ${source}
            </option>
        </#list>
        </select>
    </label><br>
    <label for="">коммент от клиента<input type="text" name="commnetFromClient" placeholder="commnetFromClient"></label><br>
    <label for="">наш комментарий<input type="text" name="commentFromManager"
                                        placeholder="commentFromManager"></label><br>
    <label for="">теги<input type="text" name="tagsAboutApplication" placeholder="tagsAboutApplication "></label><br>
    <label for="">будующий курс<input type="text" name="futureCourse" placeholder="futureCourse"></label><br>
<#if clients?? >
    <label for=""> клиент
        <select name="client" id="">
            <#list clients as client>
                <option value="${client.id}">${client.name} ${client.surname} </option>
            </#list>
        </select>
    </label>
</#if><br>
    <label for=""> курс
        <select name="course" id="">
        <#list courses as course>
            <option value="${course.id}">${course.courseTitle}</option>
        </#list>
        </select>

    </label><br>
    <label for="">скидка
        <input type="number" min="0" name="discount" placeholder="discount">
    </label><br>
    <input type="hidden" name="appCloseDate" id="closeDate">
    <input type="submit" name="" placeholder="">
</form>

<h3>создание группы</h3>
<form action="/createGroup" method="post">
    <label for="course">
        Выбрать курс

        <select class="js-example-basic-single" id="course" name="course">
        <#list courses as course>
            <option value="${course.id}">${course.courseTitle}</option>
        </#list>
        </select>
    </label>
    <br>
    <input type="text" name="groupIdentifier" placeholder="groupIdentifier">
    <br>
    <input type="datetime-local" name="startDate" placeholder="startDate">
    <br>
    <input type="text" name="room" placeholder="room">
    <input type="submit" name="" placeholder="">


</form>

<h3>наполнить группу</h3>
<form action="/fillGroup" method="post">

    <label for="group"> Выбрать группу
        <select name="group" id="group">
        <#list groups as group>
            <option value="${group.id}">
            ${group.course.courseTitle}
            ${group.groupIdentifier}
            ${group.startDate?datetime?string("yyyy-MM-dd HH:mm")}
            </option>
        </#list>
        </select>

    </label>
    <label for="clients"> Выбрать клиентов
        <select class="js-example-basic-multiple js-states form-control" id="clients" name="clients"
                multiple="multiple">
        </select>

    </label>

    <input type="submit" name="" placeholder="">

</form>


<#--<script src="/script/recomendationAjaxSearch.js"></script>-->
<script src="/script/spyScript.js"></script>
<script src="/script/select2.js"></script>
<script src="/script/findClients.js"></script>
</body>
</html>