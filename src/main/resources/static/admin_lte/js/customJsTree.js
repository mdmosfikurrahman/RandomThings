$(document).ready(function () {

    function makeTreeItem(el) {
        return $("<a>", {
            id: $(el).attr("id") + "_anchor",
            class: "jstree-anchor",
            href: "#"
        });
    }



    $.ajax({
        type: 'GET',
        async: true,
        url: "/dashboard/deptList",
        dataType: 'JSON',
        success: function (json) {
            $('#tree').jstree({
                core: {
                    check_callback: true,
                    data: json
                },
                types: {
                    root: {
                        icon: "fa fa-globe-o"
                    }
                },
                plugins: ["dnd", "types"]
            });

            $('#tagList li').draggable({
                cursor: 'move',
                helper: 'clone',
                start: function(e, ui) {
                    var item = $("<div>", {
                        id: "jstree-dnd",
                        class: "jstree-default"
                    });
                    $("<i>", {
                        class: "jstree-icon jstree-er"
                    }).appendTo(item);
                    item.append($(this).text());

                    let idRoot = $(this).attr("id").slice(0, -2);
                    let newId = idRoot + "-" + ($("#tree [id|='" + idRoot + "'][class*='jstree-node']").length + 1);
                    return $.vakata.dnd.start(e, {
                        jstree: true,
                        obj: makeTreeItem(this),
                        nodes: [{
                            id: newId,
                            text: $(this).text(),
                            icon: "fa fa-flag-o"
                        }]
                    }, item);
                }
            });
        }
    });
});


    $.ajax({
        type: 'GET',
        async: true,
        url: "/dashboard/menuList",
        dataType: 'JSON',
        success: function (json) {

            for (let i=0; i<json.length; i++) {
                $("#m_").attr('id', "m_"+json[i].mid);
                $("#m_"+json[i].mid).text(json[i].label);
            }
            let b = "<ul>";
        }
    });