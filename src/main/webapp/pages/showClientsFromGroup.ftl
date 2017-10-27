<#include "templates/header.ftl">
<form action="/fillGroup" method="post">
    <label for="clients"> Выбрать клиентов
        <select class="js-example-basic-multiple js-states form-control" id="clients" name="clients"
                multiple="multiple">
            <#list otherClients as otherClient>
                <option value="${otherClient.id}">${otherClient.name}${otherClient.surname}</option>
            </#list>
        </select>
    </label>
    <input type="hidden" name="group" value="${group.id}">
    <input type="submit" name="" placeholder="">
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
        <td>Exit</td>
    </tr>
    </thead>
<#list clients as client>
    <tr class="${client.id}">
        <td class="name"><a href="/client/${client.id}">${client.name}</a></td>
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
            <#list client.tagsAboutClient as tag> ${tag}<#sep >,</#list>
        </td>
        <td>lorem ipsum</td>
        <td>
            <form action="/deleteFromGroup" method="post">
                <input type="hidden" name="groupId" value="${group.id}">
                <input type="hidden" name="clientId" value="${client.id}">
                <input type="submit" value="Exit">
            </form>
        </td>
    </tr>
</#list>
<script src="/script/spyScript.js"></script>
<script src="/script/recomendationAjaxSearch.js"></script>
<script src="/script/select2.js"></script>
</body>
</html>