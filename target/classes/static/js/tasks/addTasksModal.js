var tasksId;
$('#addTasksModal').on(
    'show.bs.modal',
    function (event) {
        var button = $(event.relatedTarget);
        tasksId = button.data("tasksId");
    });

$("#addTasksBtn").click(function () {
    addTasks();
});

function addTasks() {
    var taskTitle = $("#taskTitle").val();
    var taskDetails = $("#taskDetails").val();
    var taskPriority = $("#taskPriority").val();
    var taskStatus = $("#taskStatus").val();
    var taskIsActive = $("#taskIsActive").val();

    var postBodyData = {
        "taskTitle": taskTitle,
        "taskDetails": taskDetails,
        "taskPriority": taskPriority,
        "taskStatus": taskStatus,
        "taskIsActive": taskIsActive
    };

    $.ajax({
        url:"/task/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(postBodyData),
        async: true,

        success: function (message){
            console.log(message)

            if (message === "success") {
                console.log(message)

                $("#taskTitle").val(message.taskTitle);
                $("#taskDetails").val(message.taskDetails);
                $("#taskPriority").val(message.taskPriority);
                $("#taskStatus").val(message.taskStatus);
                $("#taskIsActive").val(message.taskIsActive);

                initTable();
            }
        },
        error: function (message) {
            console.log(postBodyData)
            console.log(message)
        }
    })
}