package taskpool;


public class QueuedTask {
    private int taskId;

    private TaskStatus.Status status;

    private Task task;

    private int queuePosition;

    public int getTaskId() {

        return taskId;

    }

    public void setTaskId(int taskId) {

        this.taskId = taskId;

    }

    public TaskStatus.Status getStatus() {

        return status;

    }

    public void setStatus(TaskStatus.Status status) {

        this.status = status;

    }

    public Task getTask() {

        return task;

    }

    public void setTask(Task task) {

        this.task = task;

    }

    public int getQueuePosition() {

        return queuePosition;

    }

    public void setQueuePosition(int queuePosition) {

        this.queuePosition = queuePosition;

    }
}
