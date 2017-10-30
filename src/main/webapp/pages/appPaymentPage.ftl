<#include "templates/header.ftl">
<form action="/createPayment" method="post">
    <label for="">Сумма<input type="number" name="amount"></label>
    <label for="">Дата платежа<input type="datetime-local" name="dateOfPayment"</label>
    <input type="hidden" name="appId" value="${application.id}">
    <input type="submit" name="" placeholder="">
</form>

<table class="table table-hover" data-table='true' path="">
    <thead>
    <tr class="bg-primary">
        <th>Сумма</th>
        <th>Дата платежа</th>
    </tr>
    </thead>
    <#list payments as payment>
        <tr entityID="${payment.id}">
            <td field="amount">${payment.amount}</td>
            <td field="dateOfPayment">${payment.dateOfPayment?string("yyyy")}</td>
        </tr>
    </#list>
</table>

</body>
</html>

<script>
    $("input[type='submit']").click(function (e) {
        let amount = $("input[name='amount']").val();
        if (amount <= 0) {
            e.preventDefault();
        } else {
            let check = prompt("Подтвертиде платеж в размерe " + amount +" Введити сумму", "0");
            if (check != amount)
                e.preventDefault();
        }
    });
</script>

<script src="/script/includeDataTable.js"></script>