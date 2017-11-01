<#include "templates/header.ftl"/>

<form action="/createFakeAccount" method="post">
    <label for="">Логин<input type="text" name="login" placeholder="login" required></label>
    <label for="">Пароль<input type="text" name="password" placeholder="password" required></label>
    <label for="">урл<input type="url" name="url" placeholder="url" required></label>
    <label for="">Дата регистрации<input type="date" name="registrationDate" placeholder="registrationDate"
                                         required></label>
    <label for="">Дата последнего визита<input type="date" name="lastVisitDate" placeholder="lastVisitDate"
                                               required></label>
    <label for="">Коммент<input type="text" name="accComment" placeholder="comment" required></label>
    <input type="hidden" name="fakeUserId" value="${fakeUser.id}">
    <input type="submit" class="btn btn-success btn-sm" name="" placeholder="">
</form>

<table class="table table-hover" data-table='true' path="/liveEditFakeUser">
    <thead class="bg-primary">
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Телефон</th>
        <th>Мейл</th>
        <th>Комментарии</th>
        <th>Изображения</th>
    </tr>
    </thead>
    <tr entityID="${fakeUser.id}">
        <td field="name"><a href="/fakeUser/${fakeUser.id}">${fakeUser.name}</a></td>
        <td field="surname">${fakeUser.surname}</td>
        <td field="phone" type="phone">${fakeUser.phone}</td>
        <td field="email" type="email">${fakeUser.email}</td>
        <td field="fakeUserComments" type="array">
            <#list fakeUser.fakeUserComments as comment>
                 ${comment}<#sep >;
             </#list>
        </td>
        <td edit="false">
            <ul>
            <#list fakeUser.images as image>
                <li style="float: left"><img src="../${image}" height="30px" width="30px"></li>
            </#list>
            </ul>
        </td>
    </tr>
</table>

<table class="table table-hover" path="/liveEditFakeAccount">
    <thead class="bg-primary">
    <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>урл</th>
        <th>Дата регистрации</th>
        <th>Дата последнего визита</th>
        <th>Комменты</th>
        <th>Удалить</th>
    </tr>
    </thead>
<#list fakeUser.fakeAccounts as fakeAccount>
    <tr entityID="${fakeAccount.id}">
        <td field="login">${fakeAccount.login}</td>
        <td field="password">${fakeAccount.password}</td>
        <td field="siteUri">${fakeAccount.siteUri?string}</td>
        <td field="registrationDate" type="date">${fakeAccount.registrationDate?string('yyyy-MM-dd')}</td>
        <td field="lastVisitDate" type="date">${fakeAccount.lastVisitDate?string('yyyy-MM-dd')}</td>
        <td field="fakeAccountComments" type="array">
            <#list fakeAccount.fakeAccountComments as comment>
                ${comment}<#sep >;
            </#list>
        </td>
        <td edit="false">
            <form action="/deleteAccount" method="post"
                  style="display: inline-block">
                <input type="hidden" name="accountId"
                       value="${fakeAccount.id}"/>
                <input type="submit" class="btn btn-danger btn-sm" value="Delete"/>
            </form>
        </td>
    </tr>
</#list>
</table>

<script src="/script/spyScript.js"></script>
<script src="/script/recomendationAjaxSearch.js"></script>
<script src="/script/select2.js"></script>
<script src="/script/edits/liveEdit.js"></script>
<script src="/script/includeDataTable.js"></script>
</body>
</html>