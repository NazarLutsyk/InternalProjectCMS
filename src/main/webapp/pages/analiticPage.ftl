<#include "templates/header.ftl">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<h3>Statistic by socials:</h3>
<div>
    <select class="js-example-basic-multiple js-states" id="sources" name="sources"
            multiple="multiple">
    <#if sources??>
        <#list sources as source>
            <option value="${source.id}">${source.name}</option>
        </#list>
    </#if>
    </select>
    <input name="startDate" type="date">
    <input name="endDate" type="date">
    <button id="selectByPeriod" class="btn btn-info btn-sm">Select by period</button>
    <div id="container" style="width: 75%;">
        <canvas id="canvas"></canvas>
    </div>
</div>
<h3>Statistic by applications:</h3>
<div>
    <select class="js-example-basic-multiple js-states" id="courses" name="courses"
            multiple="multiple">
    <#if courses??>
        <#list courses as course>
            <option value="${course.id}">${course.courseTitle}</option>
        </#list>
    </#if>
    </select>
    <input name="start" type="date">
    <input name="end" type="date">
    <button id="selectAppsStat" class="btn btn-info btn-sm">Select by period</button>
    <div id="appStatContainer">
        <h1 style="color: green"></h1>
    </div>
</div>


<script src="/script/spyScript.js"></script>
<script src="/script/select2.js"></script>
<script src="script/analitics.js"></script>
</body>
</html>