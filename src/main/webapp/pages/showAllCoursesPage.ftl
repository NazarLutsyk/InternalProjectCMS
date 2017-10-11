<#include "templates/header.ftl">

<h2>Создание курс</h2>
<form action="/createCourse" method="post">
    <label>название курса<input type="text" name="courseTitle" placeholder="course title"></label><br>
    <label>полная стоимость<input type="number" min="0" name="fullPrice" placeholder="full price"></label><br>

    <input type="submit" name="">
</form>
<table class="table table-hover">
    <thead>
    <tr class="bg-primary">
        <th>название</th>
        <th>цена</th>

    </tr>
    </thead>
<#list courses as course>
    <tr courseID="${course.id}">
        <td field="courseTitle">${course.courseTitle}</td>
        <td field="fullPrice">${course.fullPrice?c}</td>

    </tr>
</#list>
</table>
<script>
    $('td').dblclick(function () {
        $(this).attr("contenteditable", 'true').focus();
    });

    $('td').blur(function () {
        let course = {};
        let $parent = $(this).parent();
        $parent.children().each(function () {
            course[$(this).attr('field')] = $(this).text();
            course.id = $parent.attr('courseID');
        });
        console.log(course);


        $.ajax("/liveEditCourse", {
            type: 'POST',
            data: JSON.stringify(course),
            contentType: 'application/json;charset=utf-8',
            success: function () {
            },
            error: function (err) {
                console.log(err);
                alert("no!");
            }
        });
    });

</script>
</body>

</html>