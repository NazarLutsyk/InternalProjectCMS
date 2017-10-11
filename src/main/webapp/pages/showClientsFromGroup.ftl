<#include "templates/header.ftl">
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
</body>
</html>