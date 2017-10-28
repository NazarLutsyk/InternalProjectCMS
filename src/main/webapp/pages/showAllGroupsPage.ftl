<#include "templates/header.ftl">
<form action="/showAllGroups" method="get">
    <label for="">Filter by:</label>
    <select name = "course">
        <option value="">
            All courses
        </option>
        <#list courses as course>
            <option value="${course.courseTitle}">
                ${course.courseTitle}
            </option>
        </#list>
    </select>
    <input type="date" name="startDate">
    <input type="date" name="endDate">
    <input type="submit">
</form>
<table id="groupsTable" class="table table-hover" path="/liveEditGroup">
    <thead>
    <tr class="bg-primary">
        <td>Группа</td>
        <td>Курс</td>
        <td>комната</td>
        <td>Дата старта</td>
        <td>Клиенты</td>
    </tr>
    </thead>
<#list groups as group>
    <tr entityID="${group.id}">
        <td field="groupIdentifier"><a href="/showClientsFromGroup/${group.groupIdentifier}">${group.groupIdentifier}</a></td>
        <td edit="false">${group.course}</td>
        <td field = "room">
            <#if group.room??> ${group.room}<#else>room undefined</#if>
        </td>
        <#--todo change date format-->
        <td field = "startDate" type="date">${group.startDate?string("yyyy/MM/dd HH:mm")}</td>
        <td edit="false">
            <ol>
                <#list group.clients as client>
                    <li><a href="/client/${client.id}">${client.name} ${client.surname} ${client.phoneNumber}</a></li>
                </#list>
            </ol>
        </td>
    </tr>
</#list>
</table>
<script src="/script/edits/liveEdit.js"></script>
</body>
</html>