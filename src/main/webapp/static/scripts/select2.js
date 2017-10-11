$(document).ready(function () {
    $(".js-example-basic-single").select2();
    $(".js-example-basic-multiple").select2();
    $(".js-example-placeholder-single").select2({
        placeholder: "рекомендации",
        allowClear: true
    });
});

