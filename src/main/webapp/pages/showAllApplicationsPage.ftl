<#include "templates/header.ftl"><h2>создание заявки</h2>
<form action="/createApplication" method="post">
    <label for="">время подачи заявки<input type="datetime-local" name="appReciveDate"
                                            placeholder="appReciveDate"></label>
    <label for="">откуда узнал
        <select name="source">
        <#list sources as source>
            <option value="${source}">
            ${source}
            </option>
        </#list>
        </select>
    </label>
    <label for="">коммент от клиента<input type="text" name="commnetFromClient" placeholder="commnetFromClient"></label>
    <label for="">наш комментарий<input type="text" name="commentFromManager"
                                        placeholder="commentFromManager"></label>
    <label for="">теги<input type="text" name="tagsAboutApplication" placeholder="tagsAboutApplication "></label>
    <label for="">будующий курс<input type="text" name="futureCourse" placeholder="futureCourse"></label>
<#if clients?? >
    <label for=""> клиент
        <select name="client" id="">
            <#list clients as client>
                <option value="${client.id}">${client.name} ${client.surname} </option>
            </#list>
        </select>
    </label>
</#if>
<#if courses??>
    <label for=""> курс
        <select name="course" id="">
            <#list courses as course>
                <option value="${course.id}">${course.courseTitle}</option>
            </#list>
        </select>

    </label>
</#if>
    <label for="">скидка
        <input type="number" min="0" name="discount" placeholder="discount">
    </label>
    <input type="hidden" name="appCloseDate" id="closeDate">
    <input type="submit" name="" placeholder="">
</form>
<table class="table table-hover">
    <thead class="bg-primary">
    <tr>
        <td>client</td>
        <td>course</td>
        <td>дата</td>
        <td>источник</td>
        <td>ком клиента</td>
        <td>ком мен</td>
        <td>теги</td>
        <td>будующий курс</td>
        <td>дата создания</td>
        <td>isChecked</td>
    </tr>
    </thead>
<#list applications as app>
    <tr>
        <td><a href="/client/${app.client.id}">${app.client.name} ${app.client.surname}</a></td>
        <td>${app.course.courseTitle}</td>
        <td>${app.appReciveDate?datetime}</td>
        <td>${app.source}</td>
        <td>${app.commnetFromClient}</td>
        <td>${app.commentFromManager}</td>
        <td>
            <#list app.tagsAboutApplication as tag>
                <p>${tag}</p>
            </#list>

        </td>

        <td>${app.futureCourse}</td>
        <td>${app.appCloseDate}</td>
        <td>
            <input name="appChecker"
                   type="checkbox"
                   <#if app.checked?string == 'true'>checked</#if>
                   data-appId="${app.id}"
            >
        </td>

    </tr>

</#list>

</table>

<script src="/script/reverseAppCheck.js"></script>
<script src="/script/spyScript.js"></script>
<script src="/script/recomendationAjaxSearch.js"></script>
<script src="/script/select2.js"></script>

</body>
</html>