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

<table>

<#list clients as client>
    <tr>
        <td>${client.name}</td>
        <td>${client.surname}</td>
        <td>${client.phoneNumber}</td>
        <td>${client.email}</td>
        <td>${client.city}</td>
        <td>${client.commentAboutClient}</td>
        <td>
            <#list client.tagsAboutClient as tag>
                <p>${tag}</p>
            </#list>
        </td>
        <td>${client.socials}</td>
        <td>${client.thx}</td>

    </tr>

</#list>
</table>

</body>
</html>