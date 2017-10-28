<#include "templates/header.ftl">
<form action="/fillGroup" method="post">
    <label for="clients"> Выбрать клиентов
        <select class="js-example-basic-multiple js-states form-control" id="clients" name="clients" required
                multiple="multiple">
        <#list otherClients as otherClient>
            <option value="${otherClient.id}">${otherClient.name}${otherClient.surname}</option>
        </#list>
        </select>
    </label>
    <input type="hidden" name="group" value="${group.id}">
    <input type="submit" name="" placeholder="">
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
        <td>Exit</td>
    </tr>
    </thead>
<#list clients as client>
    <tr entityID="${client.id}" class="${client.id}">
    <td field="name" class="name"><a href="/client/${client.id}">${client.name}</a></td>
    <td field="surname" class="surname">${client.surname}</td>
    <td field="phoneNumber" class="phoneNumber">${client.phoneNumber}</td>
    <td field="email" class="email">${client.email}</td>
    <td field="city" class="city">${client.city}</td>
    <td class="commentAboutClient" edit="false">
        <#list client.commentsAboutClient as comm>
        ${comm.text}<br>
        </#list>
    </td>
    <td class="tagsAboutClient" edit="false">
        <#list client.tagsAboutClient as tag> ${tag}<#sep >,</#list>
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
<script src="/script/spyScript.js"></script>
<script src="/script/recomendationAjaxSearch.js"></script>
<script src="/script/select2.js"></script>
<script src="/script/edits/liveEdit.js"></script>
</body>
</html>