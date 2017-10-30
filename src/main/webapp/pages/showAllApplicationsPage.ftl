<#include "templates/header.ftl">
<h2>создание заявки</h2>
<form action="/createApplication" method="post">
    <label for="">время подачи заявки<input type="datetime-local" name="appReciveDate"
                                            placeholder="appReciveDate"></label>
    <label for="">откуда узнал
        <select name="source">
        <#list sources as source>
            <option value="${source.id}">
                ${source.name}
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
    <label>Find unpaid applications<input type="submit" name="" placeholder=""></label>
</form>
<form action="/showAllApplications" method="get">
    <input type="hidden" name="notPaid" value="true">
    <input type="submit">
</form>
<table id="applicationsTable" class="table table-hover" data-table='true' path="/liveEditApplication">
    <thead class="bg-primary">
    <tr>
        <th>client</th>
        <th>платежи</th>
        <th>course</th>
        <th>дата</th>
        <th>источник</th>
        <th>ком клиента</th>
        <th>ком мен</th>
        <th>теги</th>
        <th>будующий курс</th>
        <th>дата создания</th>
        <th>цена со скидкой</th>
        <th>скидка %</th>
        <th>оплачено</th>
        <th>осталось заплатить</th>
        <th>isChecked</th>
    </tr>
    </thead>
<#list applications as app>
    <tr entityID="${app.id}">
        <td edit="false"><a href="/client/${app.client.id}">${app.client.name} ${app.client.surname}</a></td>
        <td edit="false"><a href="/payments/${app.id}">Смотреть</a></td>
        <td edit="false">${app.course.courseTitle}</td>
        <td field="appReciveDate" type="date">${app.appReciveDate?string("yyyy/MM/dd HH:mm")}</td>
        <td edit="false">${app.source.name}</td>
        <td field="commnetFromClient">${app.commnetFromClient}</td>
        <td field="commentFromManager">${app.commentFromManager}</td>
        <td field="tagsAboutApplication" type="array">
            <#list app.tagsAboutApplication as tag>
                ${tag}<#sep >;
            </#list>
        </td>
        <td field="futureCourse">${app.futureCourse}</td>
        <td edit="false">${app.appCloseDate}</td>
        <td edit="false">${app.priceWithDiscount}</td>
        <td edit="false">${app.discount}</td>
        <td edit="false">${app.paid}</td>
        <td edit="false">${app.leftToPay}</td>
        <td type="checker" edit="false">
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
<script src="/script/edits/liveEdit.js"></script>
<script src="/script/includeDataTable.js"></script>
</body>
</html>