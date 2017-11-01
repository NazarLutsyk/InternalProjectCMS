<#include "templates/header.ftl">
<h2>Create source</h2>
<form action="/createSource" method="post">
    <input type="text" name="name" placeholder="Name" required>
    <input type="submit" class="btn btn-success btn-sm">
</form>
<table class="table table-hover" data-table='true' path="/liveEditSocial">
    <thead>
        <tr class="bg-primary">
            <th>название</th>
            <#--<td>delete</td>-->
        </tr>
    </thead>
    <#list socials as social>
    <tr entityID="${social.id}">
        <td field="name">${social.name}</td>
        <#--<td edit="false">-->
            <#--<a href="/deleteSource/${social.id}">delete</a>-->
        <#--</td>-->
    </tr>
    </#list>
</table>
</body>
</html>
<script src="/script/edits/liveEdit.js"></script>
<script src="/script/includeDataTable.js"></script>