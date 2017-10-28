<#include "templates/header.ftl">

<h2>Создание курс</h2>
<form action="/createCourse" method="post">
    <label>название курса<input type="text" name="courseTitle" placeholder="course title"></label><br>
    <label>полная стоимость<input type="number" min="0" name="fullPrice" placeholder="full price"></label><br>

    <input type="submit" name="">
</form>
<table class="table table-hover" path="/liveEditCourse">
    <thead>
    <tr class="bg-primary">
        <th>название</th>
        <th>цена</th>
    </tr>
    </thead>
<#list courses as course>
    <tr entityID="${course.id}">
        <td field="courseTitle">${course.courseTitle}</td>
        <td field="fullPrice">${course.fullPrice?c}</td>
    </tr>
</#list>
</table>

</body>
</html>

<script src="/script/edits/liveEdit.js"></script>