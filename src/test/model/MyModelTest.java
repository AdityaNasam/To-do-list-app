package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MyModelTest {
    //delete or rename this class!
    private WorkRoom testList;
    private WorkRoom testCompleted;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    @BeforeEach
    void runBefore() {

        testList = new WorkRoom ("Buy Groceries");
        testList.addTask(new Task("Buy Groceries"));

    }

    @Test
    void testConstructor() {
        //assertEquals(1, testList.getTasks());
        assertEquals("Buy Groceries", testList.getName());

    }



    @Test
    void testAdd() {
        testList.addTask(new Task("Submit A1"));
        assertEquals(2, testList.numTasks());
        List<Task> tasks = testList.getTasks();

        String a = " ";
        for (Task t : tasks) {
            a = t.getDescription();

        } assertEquals("Submit A1", a);
    }

    @Test
    void testDelete() {
        testList.remove(0);
        assertEquals(0, testList.numTasks());
        // List <Task> tasks = testList.getTasks();
        // String a = " ";
//        for (Task t : tasks) {
//            a = t.getDescription();
//
//        } assertEquals(null, a);
    }

    @Test
    void testMultipleAdd() {
        testList.addTask(new Task("Visit Library"));
        testList.addTask(new Task("Buy a book"));
        assertEquals(3, testList.numTasks());
    }

    @Test
    void testMultipleDelete() {
        testList.addTask(new Task("Buy a book"));
        testList.addTask(new Task("Visit Library"));
        testList.remove(2);
        testList.remove(1);
        assertEquals(1, testList.numTasks());
    }

    @Test
    void testSave() {
        testList.addTask(new Task("Hey! Submit Homework"));
        testList.jsonWriter = new JsonWriter("./data/AllTasks.txt");
        testList.jsonReader = new JsonReader("./data/AllTasks.txt");
        try {
            testList.jsonWriter.open();
            testList.jsonWriter.write(testList);
            testList.jsonWriter.close();
        }catch (Exception e) {
            assertEquals(1,2);
        }

    }

    @Test
    void testException() {
        try {
            testList.getCount();
            fail();
        } catch (NoSuchFieldException e) {
            System.out.println("It is a checked Exception");
        }

    }

    @Test
    void testUncheckedException() {
        try {
            testList.remove(-1);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("It is a unchecked exception");
        }
    }



}