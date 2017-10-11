let tds = document.getElementsByTagName("td");
// for (let td of tds) {
//     td.setAttribute("contenteditable", "true");
//     // td.setAttribute("tabindex", "");
// }

for (let td of tds) {
    td.ondblclick = function (edc) {
        console.log("dbclick");
        td.setAttribute("contenteditable", "true");
        td.focus();
        let parentTR = edc.target.parentElement;
        let children = parentTR.children;
        var currentClient = {};
        for (let childTD of children) {
            currentClient[childTD.getAttribute("class")] = childTD.innerText;
        }
        td.onblur = function (eob) {
            if (this.innerText != currentClient[this.getAttribute('class')]) {
                currentClient.id = parentTR.getAttribute("class");
                currentClient[this.getAttribute('class')] = this.innerText;
                parseTagsAboutClient(currentClient);
                $.ajax("/editNow", {
                    type: "POST",
                    data: JSON.stringify(currentClient),
                    contentType: "application/json;charset=utf-8",
                    success: function (/*res*/) {
                        // let kids = parentTR.children;
                        // for (let field in res) {
                        //     for (let kid of kids) {
                        //         if (kid.getAttribute("class") == field) {
                        //             kid.innerText = res[field];
                        //         }
                        //     }
                        // }
                    },
                    error: function () {
                        console.log("no");
                    }

                });

            } else {
                console.log("request not processed because field value is the same");
            }
            td.removeAttribute("contenteditable");
        }
    }
}

function parseTagsAboutClient(obj) {
    obj.tagsAboutClient = obj.tagsAboutClient.replace(" ", "").split(",");
}
