<#include "templates/header.ftl">
<label for="">Enter comment about client:</label>
<form action="/addCommentAboutClient-${client.id}" method="post">
    <input type="text" name="text" placeholder="comment">
    <input type="submit">
</form>
<table class="table table-hover">
    <thead>
    <tr class="bg-primary">
        <td>имя</td>
        <td>фамилия</td>
        <td>телефон</td>
        <td>мыло</td>
        <td>город</td>
        <td>ком клиента</td>
        <td>теги</td>
        <td>additional info</td>
    </tr>
    </thead>
    <tr class="${client.id}">
        <td class="name">${client.name}</td>
        <td class="surname">${client.surname}</td>
        <td class="phoneNumber">${client.phoneNumber}</td>
        <td class="email">${client.email}</td>
        <td class="city">${client.city}</td>
        <td class="commentAboutClient">
            <#list client.commentsAboutClient as comm>
                ${comm.text}<br>
            </#list>
        </td>
        <td class="tagsAboutClient">
        <#list client.tagsAboutClient as tag>${tag}<#sep >,</#list>
        </td>
        <td>lorem ipsum</td>
    </tr>
</table>

<table class="table table-hover">
    <thead class="bg-primary">
    <tr>
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
    </tr>
</#list>
</table>
<select name="otherGroups">
    <#list otherGroups as group>
        <option value="${group.id}">${group.groupIdentifier}</option>
    </#list>
</select>
<button name="addGroupButton">Add group</button>
<table class="table table-hover">
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
    <tr>
        <td>
            <select name="groups">
                <option value="${group.id}">${group.groupIdentifier}</option>
                <#list otherGroups as grp>
                    <option value="${grp.id}">${grp.groupIdentifier}</option>
                </#list>
            </select>
        </td>
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
        <td>
            <button name="deleteFromGroup" value="${group.id}">Exit</button>
        </td>
    </tr>
</#list>
</table>
</body>
<script src="/script/reverseAppCheck.js"></script>
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
    $("button[name = 'deleteFromGroup']").click(function () {
        let group = $(this).val();
        let params = {
            groupId: group,
            clientId: clientId
        };
        $.ajax({
            url:"/deleteFromGroup",
            method:"post",
            contentType:"application/json",
            data:JSON.stringify(params),
            success:function () {
                location.reload(true);
            }
        });
    });
    function reverseAppCheck() {

    }
</script>
</html>