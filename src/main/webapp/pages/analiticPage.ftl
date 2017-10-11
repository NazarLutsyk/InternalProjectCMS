<#include "templates/header.ftl">

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<input name="startDate" type="date">
<input name="endDate" type="date">
<button id="selectByPeriod">Select by period</button>

<div id="container" style="width: 75%;">
    <canvas id="canvas"></canvas>
</div>

<script src="script/graphic.js"></script>
</body>
</html>