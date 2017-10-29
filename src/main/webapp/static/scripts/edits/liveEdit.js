let editableElem = null;

$('td').dblclick(function () {
    if ($(this).attr("edit") != "false") {
        $(this).attr("contenteditable", 'true').focus();
        editableElem = $(this);
    }
});

$('td').blur(function () {
    let entity = {};
    let $parent = $(this).parent();
    let href = $(this).parent().parent().parent().attr("path");
    $parent.children().each(function () {
        if ($(this).attr("field") != undefined) {
            switch ($(this).attr("type")) {
                case "date": {
                    let regex = /\D/;
                    // console.log($(this).text().split(regex));
                    entity[$(this).attr('field')] = new Date($(this).text());
                    break;
                }
                default: {
                    entity[$(this).attr('field')] = $(this).text();
                    break;
                }
            }
            entity.id = $parent.attr('entityID');
        }
    });
    $(editableElem).attr("contenteditable", 'false');
    console.log(entity);
    $.ajax(href, {
        type: 'POST',
        data: JSON.stringify(entity),
        contentType: 'application/json;charset=utf-8',
        success: function () {
        },
        error: function (err) {
            console.log(err);
        }
    });
});
