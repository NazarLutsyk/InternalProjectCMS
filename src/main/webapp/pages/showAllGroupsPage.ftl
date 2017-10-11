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
<table class="table table-hover">
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
    <tr>
        <td><a href="/showClientsFromGroup/${group.groupIdentifier}">${group.groupIdentifier}</a></td>
        <td>${group.course}</td>
        <td>
            <#if group.room??> ${group.room}<#else>room undefined</#if>
        </td>
        <td>${group.startDate?datetime?string("yyyy-MM-dd HH:mm")}</td>

        <td>
            <ol>
                <#list group.clients as client>
                    <li><a href="/client/${client.id}">${client.name} ${client.surname} ${client.phoneNumber}</a></li>
                </#list>
            </ol>
        </td>
    </tr>
</#list>
</table>
</body>
</html>