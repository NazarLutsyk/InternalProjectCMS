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
    let errors = false;
    $parent.children().each(function () {
        if ($(this).attr("field") != undefined) {
            switch ($(this).attr("type")) {
                case "phone":
                    if (validatePhone($(this).text())) {
                        entity[$(this).attr('field')] = $(this).text();
                    }
                    else {
                        alert("Not valid phone");
                        errors = true;
                    }
                    break;
                case "email":
                    if (validateEmail($(this).text())) {
                        entity[$(this).attr('field')] = $(this).text();
                    }
                    else {
                        alert("Not valid email");
                        errors = true;
                    }
                    break;
                case "date":
                    if (validateDate($(this).text())) {
                        let date = new Date($(this).text());
                        date.setHours(date.getHours() + 2);
                        entity[$(this).attr('field')] = date;
                    }
                    else {
                        alert("Not valid date");
                        errors = true;
                    }
                    break;
                case "array":
                    let regex = /\s+/;
                    entity[$(this).attr('field')] = $(this).text().replace(regex, " ").split(";");
                    break;
                default:
                    entity[$(this).attr('field')] = $(this).text();
            }
        }
        entity.id = $parent.attr('entityID');
    });
    $(editableElem).attr("contenteditable", 'false');
    if (!errors)
        updateEntity(href, entity);
    else
        location.reload(true);
    console.log(entity);
});

function updateEntity(href, entity) {
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
}