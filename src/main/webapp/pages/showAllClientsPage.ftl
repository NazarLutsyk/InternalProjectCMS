<#include "templates/header.ftl">


<h2>создание клиента</h2>
<form action="/createClient" method="post" class="form-inline">
    <label for="">имя<input type="text" name="name" placeholder="name"></label>
    <label for="">фамилия<input type="text" name="surname" placeholder="surname"></label>
    <label for="">телефон<input type="text" name="phoneNumber" placeholder="phoneNumber"></label>
    <label for="">мыло<input type="email" name="email" placeholder="email"></label>
    <label for="">город<input type="text" name="city" placeholder="city"></label>
    <label for="">наш коммент<input type="text" name="commentAboutClient" placeholder="commentAboutClient"></label>
    <label for="">теги<input type="text" name="tagsAboutClient" placeholder="tagsAboutClient"></label>
<#--<label for=""><input type="text" id="recomendation" placeholder="рекомендация"></label>-->
    <label for="">рекомендации
        <select name="recommendation" id="" class="js-example-basic-single" placeholder="рекомендации">
            <option value="empty">empty</option>
        <#list clients as client>
            <option value="${client.id}">${client.name} ${client.surname} ${client.phoneNumber}</option>
        </#list>
        </select>
    </label>
    <input type="submit" name="" placeholder="">
</form>
<form action="/showAllClients" method="get">
    <input type="hidden" value="true" name="withoutGroups">
    <input type="submit" value="Find without groups">
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
    </tr>
</#list>
</table>
<script src="/script/editValueScript.js"></script>
<script src="/script/select2.js"></script>

</body>
</html>