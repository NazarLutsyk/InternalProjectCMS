<#include "templates/header.ftl">

<table id="clientsTable" class="table table-hover" path="/liveEditClient">
    <thead>
    <tr class="bg-primary">
        <th>имя</th>
        <th>фамилия</th>
        <th>телефон</th>
        <th>мыло</th>
        <th>город</th>
        <th>ком клиента</th>
        <th>теги</th>
    </tr>
    </thead>
    <tr entityID="${client.id}">
        <td field="name" class="name">${client.name}</td>
        <td field="surname" class="surname">${client.surname}</td>
        <td field="phoneNumber" class="phoneNumber">${client.phoneNumber}</td>
        <td field="email" class="email">${client.email}</td>
        <td field="city" class="city">${client.city}</td>
        <td field="commentsAboutClient" type="array">
        <#list client.commentsAboutClient as comm>
        ${comm}<#sep >;<br>
        </#list>
        </td>
        <td field="tagsAboutClient" type="array">
        <#list client.tagsAboutClient as tag> ${tag}<#sep >;</#list>
        </td>
    </tr>
</table>

<table id="applicationsTable" class="table table-hover" data-table='true' path="/liveEditApplication">
    <thead class="bg-primary">
    <tr>
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
<#list client.applications as app>
    <tr entityID="${app.id}">
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
<div>
    <select name="otherGroups">
    <#list otherGroups as group>
        <option value="${group.id}">${group.groupIdentifier}</option>
    </#list>
    </select>
</div>
<button name="addGroupButton">Add group</button>

<table id="groupsTable" class="table table-hover" data-table='true' path="/liveEditGroup">
    <thead>
    <tr class="bg-primary">
        <th>Группа</th>
        <th>Курс</th>
        <th>комната</th>
        <th>Дата старта</th>
        <th>Клиенты</th>
        <th>Изгнать</th>
    </tr>
    </thead>
<#list client.groups as group>
    <tr entityID="${group.id}">
        <td field="groupIdentifier" edit="false">
            <select name="groups">
                <option value="${group.id}">${group.groupIdentifier}</option>
                <#list otherGroups as grp>
                    <option value="${grp.id}">${grp.groupIdentifier}</option>
                </#list>
            </select>
        </td>
        <td edit="false">${group.course}</td>
        <td field="room">
            <#if group.room??> ${group.room}<#else>room undefined</#if>
        </td>
        <td field="startDate" type="date">${group.startDate?datetime?string("yyyy-MM-dd HH:mm")}</td>
        <td edit="false">
            <ol>
                <#list group.clients as client>
                    <li><a href="/client/${client.id}">${client.name} ${client.surname} ${client.phoneNumber}</a></li>
                </#list>
            </ol>
        </td>
        <td edit="false">
            <form action="/deleteFromGroup" method="post">
                <input type="hidden" name="groupId" value="${group.id}">
                <input type="hidden" name="clientId" value="${client.id}">
                <input type="submit" value="Exit">
            </form>
        </td>
    </tr>
</#list>
</table>

</body>
<script src="/script/reverseAppCheck.js"></script>
<script src="/script/edits/liveEdit.js"></script>

<script>
    let oldGroupId = "";
    let clientId = "${client.id}";
    $("select[name = 'groups']").click(function () {
        oldGroupId = $(this).val();
    });
    $("select[name = 'groups']").change(function () {
        let newGroup = this.options[this.selectedIndex].getAttribute("value");
        let params = {
            newGroupId: newGroup,
            oldGroupId: oldGroupId,
            clientId: clientId
        };
        $.ajax({
            url: "/changeClientGroup",
            method: "post",
            contentType: "application/json",
            data: JSON.stringify(params),
            success: function () {
                location.reload(true);
            }
        });
    });
    $("button[name = 'addGroupButton']").click(function () {
        let group = $("select[name = 'otherGroups']").val();
        let params = {
            groupId: group,
            clientId: clientId
        };
        $.ajax({
            url: "/addGroupToClient",
            method: "post",
            contentType: "application/json",
            data: JSON.stringify(params),
            success: function () {
                location.reload(true);
            }
        });
    });
</script>
<script src="/script/edits/liveEdit.js"></script>
<script src="/script/includeDataTable.js"></script>
</html>