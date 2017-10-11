// $("#recomendation").on("input", function () {
//     var val = $(this).val();
//
//     $.ajax("/findRecomendator", {
//         type: "POST",
//
//         contentType: "text/plain; charset=utf-8",
//         data: JSON.stringify(val),
//         dataType: "text",
//         success: function (res) {
//             console.log(res);
//             $("#clientsList").remove();
//
//             let resultArray = JSON.parse(res);
//             let $select = $("<select/>", {id: 'clientsList', name: "recomendation"});
//             for (let obj of resultArray) {
//                 console.log(obj.id + "TEST");
//                 $select.append($("<option/>", {value: obj.id, text: obj.name + " " + obj.surname}))
//             }
//
//             $select.insertAfter($("#recomendation"));
//
//         }
//         ,
//         error: function () {
//             alert("no");
//         }
//
//     });
// });
//
//
