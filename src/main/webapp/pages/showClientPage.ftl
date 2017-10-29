<#include "templates/header.ftl">
<label for="">Enter comment about client:</label>
<form action="/addCommentAboutClient-${client.id}" method="post">
    <input type="text" name="text" placeholder="comment">
    <input type="submit">
</form>

<table id="clientsTable" class="table table-hover" path="/liveEditClient">
    <thead>
    <tr class="bg-primary">
        <td>имя</td>
        <td>фамилия</td>
        <td>телефон</td>
        <td>мыло</td>
        <td>город</td>
        <td>ком клиента</td>
        <td>теги</td>
    </tr>
    </thead>
    <tr entityID="${client.id}">
        <td field="name" class="name"><a href="/client/${client.id}">${client.name}</a></td>
        <td field="surname" class="surname">${client.surname}</td>
        <td field="phoneNumber" class="phoneNumber">${client.phoneNumber}</td>
        <td field="email" class="email">${client.email}</td>
        <td field="city" class="city">${client.city}</td>
        <td class="commentAboutClient" edit="false">
            <ul>
                <#list client.commentsAboutClient as comm>
                    <li>
                    ${comm.text}
                    </li>
                </#list>
            </ul>
        </td>
        <td class="tagsAboutClient" edit="false">
            <#list client.tagsAboutClient as tag> ${tag}<#sep >,</#list>
        </td>
    </tr>
</table>

<table id="applicationsTable" class="table table-hover" path="/liveEditApplication">
    <thead class="bg-primary">
    <tr>
        <td>платежи</td>
        <td>course</td>
        <td>дата</td>
        <td>источник</td>
        <td>ком клиента</td>
        <td>ком мен</td>
        <td>теги</td>
        <td>будующий курс</td>
        <td>дата создания</td>
        <td>цена со скидкой</td>
        <td>скидка %</td>
        <td>оплачено</td>
        <td>осталось заплатить</td>
        <td>isChecked</td>
    </tr>
    </thead>
<#list client.applications as app>
    <tr entityID="${app.id}">
        <td edit="false"><a \href="/payments/${app.id}">Смотреть</a></td>
        <td edit="false">${app.course.courseTitle}</td>
        <td field="appReciveDate" type="date">${app.appReciveDate?string("yyyy/MM/dd HH:mm")}</td>
        <td edit="false">${app.source.name}</td>
        <td field="commnetFromClient">${app.commnetFromClient}</td>
        <td field="commentFromManager">${app.commentFromManager}</td>
        <td edit="false">
            <#list app.tagsAboutApplication as tag>
                <p>${tag}</p>
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
<select name="otherGroups">
    <#list otherGroups as group>
        <option value="${group.id}">${group.groupIdentifier}</option>
    </#list>
</select>
<button name="addGroupButton">Add group</button>

<table id="groupsTable" class="table table-hover" path="/liveEditGroup">
    <thead>
    <tr class="bg-primary">
        <td>Группа</td>
        <td>Курс</td>
        <td>комната</td>
        <td>Дата старта</td>
        <td>Клиенты</td>
        <td>Изгнать</td>
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
        <td field = "room">
            <#if group.room??> ${group.room}<#else>room undefined</#if>
        </td>
        <td field = "startDate" type="date">${group.startDate?datetime?string("yyyy-MM-dd HH:mm")}</td>
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
            url:"/addGroupToClient",
            method:"post",
            contentType:"application/json",
            data:JSON.stringify(params),
            success:function () {
                location.reload(true);
            }
        });
    });
</script>
<script src="/script/edits/liveEdit.js"></script>
</html>