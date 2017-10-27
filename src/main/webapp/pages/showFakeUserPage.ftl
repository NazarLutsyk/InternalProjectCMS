<#include "templates/header.ftl"/>

<table class="table table-hover">
    <thead class="bg-primary">
    <tr>
        <td>Имя</td>
        <td>Фамилия</td>
        <td>Телефон</td>
        <td>Мейл</td>
        <td>Комментарии</td>
        <td>Изображения</td>
    </tr>
    </thead>
    <tr>
        <td>${fakeUser.name}</td>
        <td>${fakeUser.surname}</td>
        <td>${fakeUser.phone}</td>
        <td>${fakeUser.email}</td>
        <td>
            <ul>
            <#list fakeUser.fakeUserComments as comment>
                <li>${comment}</li>
            </#list>
            </ul>
        </td>
        <td>
            <ul>
            <#list fakeUser.images as image>
                <li style="float: left"><img src="../${image}" height="30px" width="30px"></li>
            </#list>
            </ul>
        </td>
    </tr>
</table>

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
    <input type="submit" name="" placeholder="">
</form>

<table class="table table-hover">
    <thead class="bg-primary">
    <tr>
        <td>Логин</td>
        <td>Пароль</td>
        <td>урл</td>
        <td>Дата регистрации</td>
        <td>Дата последнего визита</td>
        <td>Комменты</td>
        <td>Удалить</td>
    </tr>
    </thead>
<#list fakeUser.fakeAccounts as fakeAccount>
    <tr>
        <td>${fakeAccount.login}</td>
        <td>${fakeAccount.password}</td>
        <td>${fakeAccount.siteUri?string}</td>
        <td>${fakeAccount.registrationDate?string('dd-MM-yyyy')}</td>
        <td>${fakeAccount.lastVisitDate?string('dd-MM-yyyy')}</td>
        <td>
            <ul>
                <#list fakeAccount.fakeAccountComments as comment>
                    <li>${comment}</li>
                </#list>
            </ul>
        </td>
        <td>
            <form action="/deleteAccount" method="post"
                  style="display: inline-block">
                <input type="hidden" name="accountId"
                       value="${fakeAccount.id}"/>
                <input type="submit" value="Delete"/>
            </form>
        </td>
    </tr>
</#list>
</table>

<script src="/script/spyScript.js"></script>
<script src="/script/recomendationAjaxSearch.js"></script>
<script src="/script/select2.js"></script>

</body>
</html>