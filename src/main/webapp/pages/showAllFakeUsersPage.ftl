<#include "templates/header.ftl"/>
<form action="/createFakeUser" method="post" enctype="multipart/form-data">
    <label for="">Имя<input type="text" name="name" placeholder="name" required></label>
    <label for="">Фамилия<input type="text" name="surname" placeholder="surname" required></label>
    <label for="">Телефон<input type="tel" name="phone" placeholder="phone" required></label>
    <label for="">Мейл<input type="email" name="email" placeholder="email" required></label>
    <label for="">Комментарий<input type="text" name="userComment" placeholder="comment" required></label>
    <label for="">Изображение<input type="file" name="image" placeholder="image" required></label>
    <input type="submit" name="" placeholder="">
</form>
<table class="table table-hover" path="/liveEditFakeUser">
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
<#list fakeUsers as user>
    <tr entityID = "${user.id}">
        <td field="name"><a href="/fakeUser/${user.id}">${user.name}</a></td>
        <td field="surname">${user.surname}</td>
        <td field="phone">${user.phone}</td>
        <td field="email">${user.email}</td>
        <td edit="false">
            <ul>
                <#list user.fakeUserComments as comment>
                    <li>${comment}</li>
                </#list>
            </ul>
        </td>
        <td edit="false">
            <ul>
                <#list user.images as image>
                    <li style="float: left"><img src="${image}" height="30px" width="30px"></li>
                </#list>
            </ul>
        </td>
        <td edit="false">
            <ul>
                <#list user.fakeAccounts as fakeAccount>
                    <li>${fakeAccount.siteUri}</li>
                </#list>
            </ul>
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