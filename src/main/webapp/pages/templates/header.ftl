<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, fakeUser-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <script src="/externalLibsJs/jQuery/jquery-3.2.1.min.js"></script>

    <!--bootstrap-->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <!--bootstrap-->

    <link href="/externalLibsJs/select2CSS/select2.min.css" rel="stylesheet"/>
    <script src="/externalLibsJs/select2JS/select2.min.js"></script>
    <link rel="stylesheet" href="/style/main.css">

<#--datatable-->
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
    <script src="/script/updateValidation.js"></script>
</head>
<body>
<#setting time_zone="Europe/Kyiv">
<#--<nav class="breadcrumb">-->
<#--<a class="breadcrumb-item" href="/">Home</a>-->
<#--<a class="breadcrumb-item" href="/adminPage">админка</a>-->
<#--<a class="breadcrumb-item" href="/showAllApplications">showAllApplications</a>-->
<#--<a class="breadcrumb-item" href="/showAllClients">showAllClients</a>-->
<#--<a class="breadcrumb-item" href="/showAllCourses">showAllCourses</a>-->
<#---->
<#--</nav>-->
<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">CRM</a>
            </div>
            <div class="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <li><a href="/adminPage">Админка</a></li>
                    <li><a href="/showAllApplications">Заявки</a></li>
                    <li><a href="/showAllClients">Клиенты</a></li>
                    <li><a href="/showAllCourses">Курсы</a></li>
                    <li><a href="/showAllGroups">Группы</a></li>
                    <li><a href="/analitics">Аналитика</a></li>
                    <li><a href="/showFakeUsers">Фейковые юзери</a></li>
                    <li><a href="/socials">Ресурсы</a></li>
                </ul>
                <form action="/logout" class="navbar-form navbar-right" method="post">
                    <input type="submit" value="Logout" class="btn btn-info">
                </form>
            </div>
        </div>
    </div>
</nav>
</html>