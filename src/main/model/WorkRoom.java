package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of thingies
public class WorkRoom  implements Writable {
    public JsonWriter jsonWriter;
    public JsonReader jsonReader;
    private int numOfTasks;
    private String description;
    private String name;
    private List<Task> tasks;
    private int count;

    // EFFECTS: constructs workroom with a name and empty list of thingies
    public WorkRoom(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        try {
            getCount();
        } catch (NoSuchFieldException e) {
            return null;
        }
        return description;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this workroom
    public void addTask(Task task) {
        tasks.add(task);
    }

    public int remove(int number) {
        tasks.remove(number);
        return numOfTasks;
    }

    public int getCount() throws NoSuchFieldException {
        return count;
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    // EFFECTS: returns number of thingies in this workroom
    public int numTasks() {
        return tasks.size();
    }

    public Task getTaskValue(int taskNum) {
        return tasks.get(taskNum);
    }

    public boolean isTask(Task taskName) {
        return ((getTaskNum(taskName) + 1) >= 0);
    }

    public int getTaskNum(Task taskName) {

        return tasks.indexOf(taskName);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}

