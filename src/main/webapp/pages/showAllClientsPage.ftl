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
<#--todo maybe will add search by checked app and groups?-->
<form action="/showAllClients" method="get">
    <input type="hidden" value="true" name="withoutGroups">
    <label>Find without groups or not checked apps<input type="submit" value="Find"></label>
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
<#list clients as client>
    <tr entityID="${client.id}">
        <td field="name" class="name"><a href="/client/${client.id}">${client.name}</a></td>
        <td field="surname" class="surname">${client.surname}</td>
        <td field="phoneNumber" class="phoneNumber">${client.phoneNumber}</td>
        <td field="email" class="email">${client.email}</td>
        <td field="city" class="city">${client.city}</td>
        <td edit="false">
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
</#list>
</table>
<script src="/script/edits/liveEdit.js"></script>
<script src="/script/select2.js"></script>
<script src="/script/edits/clientLiveEdit.js"></script>

</body>
</html>