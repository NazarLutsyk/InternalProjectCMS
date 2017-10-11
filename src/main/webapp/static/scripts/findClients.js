$(document).ready(function () {
    let groupId= $("#group").val();
    findClientsByGroupId(groupId);
});

$("select[name = 'group']").change(function () {
    let groupId = this.options[this.selectedIndex].getAttribute("value");
    findClientsByGroupId(groupId);
});

function findClientsByGroupId(groupId) {
    $.ajax({
        url: "/findClientsWitchNotFromGroup",
        method: "post",
        contentType: "application/json",
        data: groupId,
        success: function (clients) {
            console.log(clients);
            let $clientsSelect = $("#clients");
            $clientsSelect.html("");
            for (let i = 0; i < clients.length; i++) {
                let client = clients[i];
                let $option = $("<option/>");
                $($option).attr("value",client.id);
                $($option).text(client.name + " " + client.surname);
                $clientsSelect.append($option);
            }
        }
    });
};