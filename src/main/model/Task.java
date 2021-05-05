package model;

import org.json.JSONObject;
import persistence.Writable;

public class Task implements Writable {


    public String description;


    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Task task = (Task) o;
//        return Objects.equals(description, task.description);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(description);
//    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Task", description);
        return json;
    }
}
