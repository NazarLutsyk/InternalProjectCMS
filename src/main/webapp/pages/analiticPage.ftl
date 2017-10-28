<#include "templates/header.ftl">

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
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
<button id="selectByPeriod">Select by period</button>

<div id="container" style="width: 75%;">
    <canvas id="canvas"></canvas>
</div>

<script src="/script/spyScript.js"></script>
<script src="/script/select2.js"></script>
<script src="script/graphic.js"></script>
</body>
</html>