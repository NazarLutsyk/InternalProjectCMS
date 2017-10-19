<#include "templates/header.ftl"/>

<form action="/connectAccountsToUser" method="post">
    <label for="freeAccounts"> Выбрать аккаунты
        <select class="js-example-basic-multiple js-states form-control" id="freeAccounts" name="freeAccounts"
                multiple="multiple">
        <#list freeAccounts as fakeAccount>
            <option value="${fakeAccount.id}">${fakeAccount.siteUri}</option>
        </#list>
        </select>
    </label>
    <input type="hidden" name="userId" value="${fakeUser.id}">
    <input type="submit">
</form>

<table class="table table-hover">
    <thead class="bg-primary">
    <tr>
        <td>Имя</td>
        <td>Фамилия</td>
        <td>Телефон</td>
        <td>Мейл</td>
        <td>Комментарии</td>
        <td>Изображения</td>
        <td>Аккаунты</td>
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
        <td>
            <ul>
            <#list fakeUser.fakeAccounts as fakeAccount>
                <div>
                ${fakeAccount.siteUri}
                    <form action="/disconnectAccount" method="post"
                          style="display: inline-block; float: right; clear: both;">
                        <input type="hidden" name="disconnectAccountId"
                               value="${fakeAccount.id}"/>
                        <input type="submit" value="Отвязать"/>
                    </form>
                </div>
            </#list>
            </ul>
        </td>
    </tr>
</table>

<table class="table table-hover">
    <thead class="bg-primary">
    <tr>
        <td>Логин</td>
        <td>Пароль</td>
        <td>урл</td>
        <td>Дата регистрации</td>
        <td>Дата последнего визита</td>
        <td>Юзеp</td>
        <td>Комменты</td>
        <td>Отвязать</td>
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
            <#if fakeAccount.fakeUser??>
                 ${fakeAccount.fakeUser.name}${fakeAccount.fakeUser.surname}
            </#if>
        </td>
        <td>
            <ul>
                <#list fakeAccount.fakeAccountComments as comment>
                    <li>${comment}</li>
                </#list>
            </ul>
        </td>
        <td>
            <form action="/disconnectAccount" method="post"
                  style="display: inline-block">
                <input type="hidden" name="disconnectAccountId"
                       value="${fakeAccount.id}"/>
                <input type="submit" value="Отвязать"/>
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