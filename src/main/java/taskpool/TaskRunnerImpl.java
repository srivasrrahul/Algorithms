package taskpool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class Worker implements Runnable {

    @Override
    public void run() {

        while (true) {
            Task taskToBeHandled = null;
            QueuedTask queuedTask = null;
            Lock writeLock = reentrantReadWriteLockOnQueue.writeLock();
            try {
                writeLock.lock();
                if (taskList.size() > 0) {
                    queuedTask = taskList.get(0);
                    taskToBeHandled = queuedTask.getTask();
                    taskList.remove(0);
                    queuedTask.setStatus(TaskStatus.Status.RUNNING);
                    processedTaskHashMap.put(queuedTask.getTaskId(),queuedTask);
                }
            } finally {
                writeLock.unlock();

            }

            //If null handle the task
            handleTask(taskToBeHandled,queuedTask);
            try {
                Thread.sleep(100); //Sleeping to avoid 100% CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (runTillEternity == false) {
                break;
            }

        }

    }

    void handleTask(Task task,QueuedTask queuedTask) {
        if (null == task) {
            return;
        }


        //Now handle the task
        StringBuilder stringBuilder = new StringBuilder();

        for (String cmd : task.getCommands()) {
            stringBuilder.append(cmd + " ");
        }

        String command = stringBuilder.toString();

        int rc = 0;
        if (task.getCaptureOutput()) {
            //Acquire a write lock
            ReentrantReadWriteLock.WriteLock writeLock = globalTaskLock.writeLock();
            try {
                writeLock.lock();
                rc = runCommand(command,true);
            }finally {
                writeLock.unlock();
            }
        }else {
            //Acquire read lock
            ReentrantReadWriteLock.ReadLock readLock = globalTaskLock.readLock();
            try {
                readLock.lock();
                rc = runCommand(command,false);
            }finally {
                readLock.unlock();
            }
        }

        System.out.println("Command finished with return code " + rc);
        Lock writeLock = reentrantReadWriteLockOnQueue.writeLock();
        try {
            //Lock is required since enum is a class and writes are not atomic
            writeLock.lock();

            queuedTask.setStatus(TaskStatus.Status.COMPLETE);
        } finally {
            writeLock.unlock();

        }




    }

    int runCommand(String command,boolean requireOutput) {
        System.out.println("Running command " + command);
        //String workingDir = System.getProperty("user.dir");
        //System.out.println("Working directory is " + workingDir);

        try {
            Process proc = Runtime.getRuntime().exec( command );
            if (requireOutput) {
                InputStream inputStream = proc.getInputStream();
                OutputStream outputStream = proc.getOutputStream();
                InputStream errorStream = proc.getErrorStream();
                //Create separate thread for these streams to get input from user
            }
            proc.waitFor(); //Blocks current threads till it get finished
            return proc.exitValue();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return -1; //Internal error
    }

    Worker(List<QueuedTask> taskList,HashMap<Integer,QueuedTask> hashMap,
           ReentrantReadWriteLock reentrantReadWriteLockOnQueue,ReentrantReadWriteLock forLoneExecution) {

        this.taskList = taskList;
        this.processedTaskHashMap = hashMap;
        this.reentrantReadWriteLockOnQueue = reentrantReadWriteLockOnQueue;
        this.globalTaskLock = forLoneExecution;
    }

    void setRunTillEternity(boolean flag) {
        runTillEternity = flag;
    }
    private List<QueuedTask> taskList;
    private HashMap<Integer,QueuedTask> processedTaskHashMap;
    private ReentrantReadWriteLock reentrantReadWriteLockOnQueue;
    private ReentrantReadWriteLock globalTaskLock;
    boolean runTillEternity = true;
}

public class TaskRunnerImpl implements TaskRunner {

    public TaskRunnerImpl() {
        //worker = new Worker[4]; //take a variable
        thread = new Thread[NUMBER_OF_THREADS];
        worker = new Worker[NUMBER_OF_THREADS];
        for (int i = 0;i<NUMBER_OF_THREADS;++i) {
            worker[i] = new Worker(taskQueue,processedTask,reentrantReadWriteLockForLoneExecution,reentrantReadWriteLockForTaskQ);
            thread[i] = new Thread(worker[i]);
        }

        System.out.println("=====Task Runner started========");

    }

    @Override
    public int addTask(Task task) {
        Lock writeLock = reentrantReadWriteLockForTaskQ.writeLock();
        try {
            QueuedTask queuedTask = new QueuedTask();
            queuedTask.setTask(task);
            queuedTask.setTaskId(++taskId);
            queuedTask.setStatus(TaskStatus.Status.QUEUED);

            writeLock.lock();
            taskQueue.add(queuedTask);
        }finally {
            writeLock.unlock();
        }
        return 0;
    }

    @Override
    public void start() {
        for (int i = 0;i<NUMBER_OF_THREADS;++i) {
            thread[i].start();
        }
    }

    @Override
    public void stop() {
        //Kill the threads
        //Thread.stop is deprecated method

        for (int i = 0;i<NUMBER_OF_THREADS;++i) {
            //No need for log as we can have eventual consistent
            worker[i].setRunTillEternity(false);
        }

        for (int i = 0;i<NUMBER_OF_THREADS;++i) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<QueuedTask> tasks(TaskStatus.Status status) {
        //Iterate through big list and collect status
        //It locks big data structure and hence not advisable to run often
        List<QueuedTask> queuedTasks = new LinkedList<>();
        Lock writeLock = reentrantReadWriteLockForTaskQ.writeLock();
        try {

            writeLock.lock();
            int queuePosition = 0;
            if (status == null || status == TaskStatus.Status.QUEUED) {
                for (QueuedTask queuedTask : queuedTasks) {
                    //Should we return same handle or different handle
                    //AS MVP retunr basic
                    queuedTask.setQueuePosition(++queuePosition);
                    queuedTasks.add(queuedTask);
                }
            }

            if (status == null || status != TaskStatus.Status.QUEUED) {
                for (Map.Entry<Integer, QueuedTask> queuedTaskEntry : processedTask.entrySet()) {
                    QueuedTask val = queuedTaskEntry.getValue();
                    TaskStatus.Status taskStatus = val.getStatus();
                    if (taskStatus == TaskStatus.Status.COMPLETE && (status == null || status == TaskStatus.Status.COMPLETE)) {
                        queuedTasks.add(queuedTaskEntry.getValue());
                    }

                    if (taskStatus == TaskStatus.Status.RUNNING && (status == null || status == TaskStatus.Status.RUNNING)) {
                        queuedTasks.add(queuedTaskEntry.getValue());
                    }
                }
            }
        } finally {
            writeLock.unlock();

        }

        return queuedTasks;
    }

    @Override
    public void waitTasks() {
        for (int i = 0;i<NUMBER_OF_THREADS;++i) {
            //No need for log as we can have eventual consistent
            worker[i].setRunTillEternity(false);
        }

        for (int i = 0;i<NUMBER_OF_THREADS;++i) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean cancelTask(int taskId) {
        return false;
    }


    private Worker worker[];
    private ReentrantReadWriteLock reentrantReadWriteLockForTaskQ = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock reentrantReadWriteLockForLoneExecution = new ReentrantReadWriteLock();
    private HashMap<Integer,QueuedTask> processedTask = new HashMap<>();
    private List<QueuedTask> taskQueue = new LinkedList<>();
    private Thread thread[];
    private static final int NUMBER_OF_THREADS = 4;

    private static int taskId = 0;



    //Test Code
    public static void basicTestCase() {
        TaskRunnerImpl taskRunner = new TaskRunnerImpl();
        Task task = new Task();
        List<String> command = new LinkedList<>();
        command.add("mkdir");
        command.add("x");
        //task.setCommands(command); used this API
        taskRunner.addTask(task); //-- Add this task for testing
        taskRunner.start();
        taskRunner.addTask(task);
        List<QueuedTask> queuedTasks = taskRunner.tasks(TaskStatus.Status.QUEUED);
        System.out.println(queuedTasks);
        taskRunner.stop();

    }
    public static void main(String[] args) {
       basicTestCase();
    }
}
