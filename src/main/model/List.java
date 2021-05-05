package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;



public class List implements Writable {
    public int numOfTasks; //tracks the number of tasks
    private int count;
    ArrayList<Task> task = new ArrayList<>();//the list of tasks


    /*
     * REQUIRES: nameOfTask has a non-zero length
     * EFFECTS: task is set to nameOfTask of taskIndex in the list
     *          if taskIndex >= 0 then numOfTasks on List is set to
     *          taskIndex, otherwise numOfTasks are zero.
     */
    public List() {
//        String a = task1.getDescription();
    }

    public List(int taskIndex, Task description) {

        if (taskIndex >= 0) {
            numOfTasks = taskIndex++;
            task.add(description);

        } else {
            numOfTasks = 0;
        }
    }

    public int getCount() {
        return count;
    }


    public ArrayList<Task> getTask() {
        return task;
    }

    public Task getTaskValue(int taskNum) {
        return task.get(taskNum);
    }

    public boolean isTask(String taskName) {
        return task.contains(taskName);

    }

    public int getTaskNum(Task taskName) {

        return task.indexOf(taskName);
    }

    public int getNumOfTask() {
        return task.size();
    }


    public int add(String name) {
        numOfTasks = numOfTasks++;
        task.add(new Task(name));
        return numOfTasks;
    }

    public int remove(int number) {
        task.remove(number);
        return numOfTasks;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

    //@Override
    //public String toString() {
      //  return "List{" + "numOfTasks=" + numOfTasks + ", count=" + count + ", task=" + task.add() + '}';
    //}

    /*
     * EFFECTS: returns a string representation of List
     */
//    @Override
//    public String toString() {
//        String fullList = String.format("%.2f", task);  // get balance to 2 decimal places as a string
//        return "[ tasks = " + fullList  + ", "
//                + "number of tasks = $" + numOfTasks + "]";
//    }
}
