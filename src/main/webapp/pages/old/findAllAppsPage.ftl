<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        table {
            border: 1px solid black;
        }

        td {
            border: 1px solid red;
        }
    </style>
</head>
<body>

<#list applications>
<table>
    <tr>
        <td>Подробнее</td>
        <td>Имя</td>
        <td>Фамилия</td>
        <td>Телефон</td>
        <td>Адрес</td>
        <td>Город</td>
        <td>Комментарий</td>
        <td>Теги</td>
    </tr>
    <#items as app>
        <tr>
            <td><a href="details-${app.id}">детали заявки</a></td>
            <td>${app.client.name}</td>
            <td><a href="client-${app.client.id}">${app.client.surname}</a></td>
            <td><#if app.client.phoneNumber == "">NO NUMBER! <#else > ${app.client.phoneNumber}</#if></td>
            <td>${app.client.email}</td>
            <td>${app.client.city}</td>
            <td>${app.client.commentAboutClient}</td>
            <td>
                <#list app.client.tagsAboutClient as tag><p>${tag}</p></#list>
            </td>
            <td>${app.appReciveDate?datetime}</td>
            <td>${app.source}</td>
            <td>${app.commnetFromClient}</td>
            <td>${app.commentFromManager}</td>
            <td>
                <#list app.tagsAboutApplication as tag><p>${tag}</p></#list>
            </td>
            <td> ${app.course.courseTitle}</td>
            <td> ${app.course.startDate?datetime}</td>
            <td> ${app.course.fullPrice}</td>
            <td> ${app.course.discount}</td>
            <td> ${app.course.priceWithDiscount}</td>

        </tr>
    </#items>
</table>
</#list>

</body>
</html>




