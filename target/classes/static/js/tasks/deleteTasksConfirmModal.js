var tasksId;
$('#deleteTasksConfirmModal').on(
    'show.bs.modal',
    function (event) {
        var button = $(event.relatedTarget);
        tasksId = button.data('tasksId');
    }
)

$('#deleteTasksConfirm').click(function (){
    location.href = `/task/delete/${tasksId}`;
});