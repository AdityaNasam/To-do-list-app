package ui;

import model.Task;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TodoListApp {
    private WorkRoom log1;
    private WorkRoom completed1;
    private WorkRoom notCompleted1;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    //private List log;
    //private List completed;
    //private List notCompleted;


    private Scanner input;


    // EFFECTS: runs the todolist application
    public TodoListApp() {

        runTodoList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTodoList() {
        boolean keepGoing = true;
        String scanner = null;

        init();

        while (keepGoing) {
            displayMenu();
            scanner = input.next();
            scanner = scanner.toLowerCase();

            if (scanner.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(scanner);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addToList();
        } else if (command.equals("d")) {
            deleteFromList();
        } else if (command.equals("all")) {
            getInfo();
        } else if (command.equals("c")) {
            getCompletedInfo();
        } else if (command.equals("nc")) {
            getNotCompletedInfo();
        } else if (command.equals("mc")) {
            markAsCompleted();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes List
    private void init() {
        log1 = new WorkRoom("All Tasks workroom");
        log1.jsonWriter = new JsonWriter("./data/AllTasks.txt");
        log1.jsonReader = new JsonReader("./data/AllTasks.txt");
        completed1 = new WorkRoom("Completed Tasks workroom");
        completed1.jsonWriter = new JsonWriter("./data/CompletedTasks.txt");
        completed1.jsonReader = new JsonReader("./data/CompletedTasks.txt");
        notCompleted1 = new WorkRoom("Not-Completed Tasks workroom");
        notCompleted1.jsonWriter = new JsonWriter("./data/Not-CompletedTasks.txt");
        notCompleted1.jsonReader = new JsonReader("./data/Not-CompletedTasks.txt");
//        log = new List();
//        log1.addTask("Get the print");
//        log1.addTask("shop Groceries");
//        log1.addTask("Attend Lab at 4pm");
////        completed = new List();
////        notCompleted = new List();
//        notCompleted1.addTask("Get the print");
//        notCompleted1.addTask("shop Groceries");
//        notCompleted1.addTask("Attend Lab at 4pm");



        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add");
        System.out.println("\td -> delete");
        System.out.println("\tall -> all Tasks");
        System.out.println("\tc -> completed Tasks");
        System.out.println("\tnc -> remaining Tasks");
        System.out.println("\tmc -> mark Tasks As Completed");
        System.out.println("\ts -> save WorkRoom");
        System.out.println("\tl -> load WorkRoom");
        System.out.println("\tq -> quit");
    }

//    private void printTasks() {
//        List<Task> tasks = log1.getTasks();
//
//        for (Task t : tasks) {
//            System.out.println(t.getDescription());
//        }
//    }

    // MODIFIES: this
    // EFFECTS: adds a task to list
    private void addToList() {
        System.out.print("Mention the task you would like to add");
        Scanner in = new Scanner(System.in);;
        String taskName = in.nextLine();
        log1.addTask(new Task(taskName));
        notCompleted1.addTask(new Task(taskName));
        for (int i = 0; i < log1.numTasks(); i++) {
            Task b = log1.getTaskValue(i);
            System.out.println(b.getDescription());
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes task from list
    private void deleteFromList() {
        System.out.print("Mention the index of the task you would like to remove");
        Scanner in1 = new Scanner(System.in);;
        int taskNum = in1.nextInt();

        Task a =  log1.getTaskValue(taskNum);


        if (completed1.isTask(a)) {
            int b = completed1.getTaskNum(a);
            completed1.remove(b + 1);
        } else {
            int c = notCompleted1.getTaskNum(a);
            notCompleted1.remove(c +  1);
        }
        log1.remove(taskNum);
        for (int i = 0; i < log1.numTasks(); i++) {
            Task b = log1.getTaskValue(i);
            System.out.println(b.getDescription());
        }

        //System.out.print(log.getTask());
    }

    // EFFECTS: Save the lists to a file
    private void saveWorkRoom() {
        try {
            log1.jsonWriter.open();
            log1.jsonWriter.write(log1);
            log1.jsonWriter.close();
            completed1.jsonWriter.open();
            completed1.jsonWriter.write(completed1);
            completed1.jsonWriter.close();
            notCompleted1.jsonWriter.open();
            notCompleted1.jsonWriter.write(notCompleted1);
            notCompleted1.jsonWriter.close();
            System.out.println("Saved " + log1.getName() + " to " + "./data/AllTasks.txt");
        } catch (FileNotFoundException var2) {
            System.out.println("Unable to write to file: ./data/myFile.txt");
        }

    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            log1 = log1.jsonReader.read();
            completed1 = completed1.jsonReader.read();
            notCompleted1 = notCompleted1.jsonReader.read();
            System.out.println("Loaded " + log1.getName() + " from " + "./data/AllTasks.txt");

            List<Task> tasks = log1.getTasks();
            List<Task> tasksC = log1.getTasks();
            List<Task> tasksNc = log1.getTasks();
            init();
            for (Task t : tasks) {
                log1.addTask(t);
            }
            for (Task t : tasksC) {
                completed1.addTask(t);
            }
            for (Task t : tasksNc) {
                notCompleted1.addTask(t);
            }


        } catch (IOException e) {
            System.out.println("Unable to read from file: " + "./data/AllTasks.txt");
        }
    }

    // MODIFIES: this
    // EFFECTS: gets information about list
    private void getInfo() {
//        for (int i = 0; i < log1.numTasks(); i++) {
//            Task b = log1.getTaskValue(i);
//            System.out.println(b.getDescription());
//        }
        List<Task> tasks = log1.getTasks();

        for (Task t : tasks) {
            System.out.println(t.getDescription());
        }
        //System.out.println(log.getTask());
    }

    // MODIFIES: this
    // EFFECTS: gets information about Completed Tasks list
    private void getCompletedInfo() {
//        if (completed1.numTasks() > 0) {
//            for (int i = 0; i < completed1.numTasks(); i++) {
//                Task b = completed1.getTaskValue(i);
//                System.out.println(b.getDescription());
//            }
//        } else {
//            System.out.print("You haven't completed any task");
//        }
        if (completed1.numTasks() > 0) {
            List<Task> tasksC = completed1.getTasks();
            for (Task t : tasksC) {
                System.out.println(t.getDescription());
            }
        } else {
            System.out.println("You haven't completed any tasks");
        }
    }

    // MODIFIES: this
    // EFFECTS: gets information about Not Completed Tasks list
    private void getNotCompletedInfo() {
//        if (notCompleted1.numTasks() > 0) {
//            for (int i = 0; i < notCompleted1.numTasks(); i++) {
//                Task b = notCompleted1.getTaskValue(i);
//                System.out.println(b.getDescription());
//            }
//        } else {
//            System.out.print("Hurray!! You have completed all the tasks");
//        }
//
        if (completed1.numTasks() > 0) {
            List<Task> tasksNc = notCompleted1.getTasks();

            for (Task t : tasksNc) {
                System.out.println(t.getDescription());
            }
        } else {
            System.out.println("Hurray!! You have completed all the tasks");
        }
    }

    // MODIFIES: this
    // EFFECTS: Marks a Task as Completed
    private void markAsCompleted() {
        System.out.print("Mention the task you would like to add");
        Scanner in2 = new Scanner(System.in);
        String taskName = in2.nextLine();
        completed1.addTask(new Task(taskName));
        int a = notCompleted1.getTaskNum(completed1.getTaskValue(completed1.numTasks() - 1));
        notCompleted1.remove(a + 1);
        for (int i = 0; i < completed1.numTasks(); i++) {
            Task b = completed1.getTaskValue(i);
            System.out.println(b.getDescription());
        }
    }



//    // EFFECTS: prompts user to select all or completed or remaining tasks
//    private List selectstatus() {
//        String selection = "";  // force entry into loop
//
//        while (!(selection.equals("c") || selection.equals("s"))) {
//            System.out.println("c for chequing");
//            System.out.println("s for savings");
//            selection = input.next();
//            selection = selection.toLowerCase();
//        }
//
//        if (selection.equals("all")) {
//            return log;
//        } else if (selection.equals("c")) {
//            return log;
//        } else {
//            return log;
//        }
//
//    }
    // EFFECTS: prints List




}
