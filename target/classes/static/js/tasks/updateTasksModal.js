var tasksId;
$("#updateTasksModal").on(
    'show.bs.modal',
    function (event) {
        var button = $(event.relatedTarget);
        tasksId = button.data("tasksId");
        console.log(tasksId);
        $.ajax({
            url: "/task/" + tasksId,
            type: "GET",
            dataType: "JSON",
            success: function (message) {
                console.log(message);

                $("#updateTaskTitle").val(message.taskTitle);
                $("#updateTaskDetails").val(message.taskDetails);
                $("#updateTaskPriority").val(message.taskPriority);
                $("#updateTaskStatus").val(message.taskStatus);
                $("#updateTaskIsActive").val(message.taskIsActive);
            },
            error: function (xhr) {
                console.log('Error: ' + xhr);
            }
        });
    }
)

$("#updateTasksBtn").click(function () {
    updateTasks();
});

function updateTasks(){
    var taskTitle = $("#updateTaskTitle").val();
    var taskDetails = $("#updateTaskDetails").val();
    var taskPriority = $("#updateTaskPriority").val();
    var taskStatus = $("#updateTaskStatus").val();
    var taskIsActive = $("#updateTaskIsActive").val();

    var postBodyData = {
        "tasksId": tasksId,
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
            if (message === 'success') {
                console.log(message)
                initTable();
            }
        },
        error: function (message) {
            console.log(postBodyData)
            console.log(message)
        }
    })
}