<#include "templates/header.ftl">
<form action="/createGroup" method="post">
    <label for="course">
        Выбрать курс
        <select class="js-example-basic-single" id="course" name="course" required>
        <#list courses as course>
            <option value="${course.id}">${course.courseTitle}</option>
        </#list>
        </select>
    </label>
    <br>
    <input type="text" name="groupIdentifier" placeholder="groupIdentifier" required>
    <br>
    <input type="datetime-local" name="startDate" placeholder="startDate" required>
    <br>
    <input type="text" name="room" placeholder="room" required>
    <input type="submit" class="btn btn-success btn-sm" name="" placeholder="">
</form>
<br>
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
    <input type="submit" class="btn btn-info btn-sm">
</form>
<table id="groupsTable" class="table table-hover" data-table='true' path="/liveEditGroup">
    <thead>
    <tr class="bg-primary">
        <th>Группа</th>
        <th>Курс</th>
        <th>комната</th>
        <th>Дата старта</th>
        <th>Клиенты</th>
    </tr>
    </thead>
<#list groups as group>
    <tr entityID="${group.id}">
        <td field="groupIdentifier"><a href="/showClientsFromGroup/${group.groupIdentifier}">${group.groupIdentifier}</a></td>
        <td edit="false">${group.course}</td>
        <td field = "room">
            <#if group.room??> ${group.room}<#else>room undefined</#if>
        </td>
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
<script src="/script/includeDataTable.js"></script>
</body>
</html>