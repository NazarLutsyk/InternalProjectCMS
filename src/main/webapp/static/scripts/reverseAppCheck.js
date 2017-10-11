$("input[name = 'appChecker']").click(function () {
    let appId = $(this).attr("data-appId");
    $.ajax({
        url:"/reverseAppIsCheck",
        method:"post",
        contentType:"text/plain",
        data:appId
    });
});