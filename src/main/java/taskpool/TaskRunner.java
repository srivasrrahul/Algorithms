package taskpool;


import java.util.List;

public interface TaskRunner {
    /**

     * Adds a task to the task pool.

     * This may be called whether or not the task pool is currently

     * running.

     * @param task the task to add.

     * @return the task ID associated with the object.

     */

    int addTask(Task task);

    /**

     * This should cause the TaskRunner to begin executing tasks.

     * However, it does not need to wait for execution to begin in

     * order to return.

     */

    void start();

    /**

     * Synchronously stops the task pool.

     * This function should wait until all running (but not queued)

     * tasks have completed before returning.

     */

    void stop();

    /**

     * Returns metadata regarding tasks currently in the task pool.

     * @param status a state to filter for if specified, or null.

     * @return A list of QueuedTask objects.

     */

    List<QueuedTask> tasks(TaskStatus.Status status);

    /**

     * OPTIONAL ­ You may skip implementation of this function.

     * Wait for all tasks added to the task pool to execute.

     */

    void waitTasks();

    /**

     * OPTIONAL ­ You may skip implementation of this function.

     * Cancels the task with the given task ID if it has not been

     * started.

     * @param taskId the ID of the task to cancel, returned by addTask

     * @return true if the task was canceled, false otherwise.

     */

    boolean cancelTask(int taskId);
}
