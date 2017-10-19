<#include "templates/header.ftl"/>
<form action="/createFakeAccount" method="post">
    <label for="">Логин<input type="text" name="login" placeholder="login" required></label>
    <label for="">Пароль<input type="text" name="password" placeholder="password" required></label>
    <label for="">урл<input type="url" name="url" placeholder="url" required></label>
    <label for="">Дата регистрации<input type="date" name="registrationDate" placeholder="registrationDate"
                                         required></label>
    <label for="">Дата последнего визита<input type="date" name="lastVisitDate" placeholder="lastVisitDate"
                                               required></label>
    <label for="fakeUser"> Выбрать юзера
        <select id="fakeUserId" name="fakeUserId">
            <option value="">empty</option>
        <#list fakeUsers as user>
            <option value="${user.id}">${user.name}${user.surname}</option>
        </#list>
        </select>
    </label>
    <label for="">Коммент<input type="text" name="accComment" placeholder="comment" required></label>
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
        <td>Юзеp</td>
        <td>Комменты</td>
    </tr>
    </thead>
<#list fakeAccounts as fakeAccount>
    <tr>
        <td>${fakeAccount.login}</td>
        <td>${fakeAccount.password}</td>
        <td>${fakeAccount.siteUri?string}</td>
        <td>${fakeAccount.registrationDate?string('dd-MM-yyyy')}</td>
        <td>${fakeAccount.lastVisitDate?string('dd-MM-yyyy')}</td>
        <td>
            <#if fakeAccount.fakeUser??>
                <div>
                 <a href="/fakeUser/${fakeAccount.fakeUser.id}">
                    ${fakeAccount.fakeUser.name}${fakeAccount.fakeUser.surname}
                 </a>
                    <form action="/disconnectAccount" method="post"
                          style="display: inline-block; float: right; clear: both;">
                        <input type="hidden" name="disconnectAccountId"
                               value="${fakeAccount.id}"/>
                        <input type="submit" value="Отвязать"/>
                    </form>
                </div>
            </#if>
        </td>
        <td>
            <ul>
                <#list fakeAccount.fakeAccountComments as comment>
                    <li>${comment}</li>
                </#list>
            </ul>
        </td>
    </tr>
</#list>
</table>

</body>
</html>