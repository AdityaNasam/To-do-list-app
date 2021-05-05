package ui;

import model.Task;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class GuiMain extends JFrame implements ActionListener {
    private JLabel label;
    private JTextArea area;
    private JTextField field;
    private JList<String> list;
    private DefaultListModel listModel;


    private WorkRoom log;
    private WorkRoom completed;
    private WorkRoom notCompleted;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public GuiMain() {
        super("TODOLIST");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JFrame f = new JFrame();
        f.setSize(50,50);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.blue);
        init();
        listModel = new DefaultListModel();
        list = new JList<>();
        label = new JLabel("flag");
        area = new JTextArea(5,10);
        loadScreen();


        initialiseOne();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        // Sets "this" object as an action listener for btn
        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        // You could also set a different object, if you wanted
        // a different object to respond to the button click



        //list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setSelectedIndex(0);
        //list.addListSelectionListener(this);
        //list.setVisibleRowCount(5);

        //JScrollPane listScrollPane = new JScrollPane();
//        listScrollPane.setViewportView(list);
//        list.setLayoutOrientation(JList.VERTICAL);
        //add(listScrollPane, BorderLayout.CENTER);

    }

    public void loadScreen() {
        int n = JOptionPane.showConfirmDialog(this, "Would you like to load your file?",
                "Load Question",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            add("");
            loadTasks();
        }
    }

    public void initialiseOne() {
        JButton btn = new JButton("add");
        JButton btn1 = new JButton("markAsComplete");
        JButton btn2 = new JButton("delete");
        JButton btn3 = new JButton("all Tasks");




        btn.setActionCommand("add");
        btn.addActionListener(this);
        btn1.setActionCommand("Mc");
        btn1.addActionListener(this);
        btn2.setActionCommand("del");
        btn2.addActionListener(this);
        btn3.setActionCommand("all");
        btn3.addActionListener(this);



        field = new JTextField(5);

        add(field);
        add(list);
        add(area);
        add(btn);
        add(btn1);
        add(btn2);
        add(btn3);

        initialiseTwo();
    }


    public void initialiseTwo() {
        JButton btn4 = new JButton("completed Tasks");
        JButton btn5 = new JButton("notCompleted Tasks");
        JButton btn6 = new JButton("save");
        JButton btn7 = new JButton("load");
        JButton btn8 = new JButton("quit");
        btn4.setActionCommand("c");
        btn4.addActionListener(this);
        btn5.setActionCommand("nc");
        btn5.addActionListener(this);
        btn6.setActionCommand("s");
        btn6.addActionListener(this);
        btn7.setActionCommand("l");
        btn7.addActionListener(this);
        btn8.setActionCommand("q");
        btn8.addActionListener(this);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
    }



    // EFFECTS: Plays Music
    private static void playMusic() {
        InputStream music;
        try {
            music = new FileInputStream(new File("./data/StarWars.wav"));


            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            System.out.println("no");

        }

    }

    public void init() {
        log = new WorkRoom("All Tasks workroom");
        log.jsonWriter = new JsonWriter("./data/AllTasks.txt");
        log.jsonReader = new JsonReader("./data/AllTasks.txt");
        completed = new WorkRoom("Completed Tasks workroom");
        completed.jsonWriter = new JsonWriter("./data/CompletedTasks.txt");
        completed.jsonReader = new JsonReader("./data/CompletedTasks.txt");
        notCompleted = new WorkRoom("Not-Completed Tasks workroom");
        notCompleted.jsonWriter = new JsonWriter("./data/Not-CompletedTasks.txt");
        notCompleted.jsonReader = new JsonReader("./data/Not-CompletedTasks.txt");
    }

    //MODIFIES: Jlist
    //EFFECTS: Adds the tasks from workroom to JList
    private void add(String name) {
        list.setModel(listModel);
        listModel.addElement(name);



    }

    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            addTask();

        } else if (e.getActionCommand().equals("Mc")) {
            markCTask();

        } else if (e.getActionCommand().equals("del")) {
            delTask();

        } else if (e.getActionCommand().equals("all")) {
            allTasks();

        } else if (e.getActionCommand().equals("c")) {
            completedTasks();

        } else if (e.getActionCommand().equals("nc")) {
            notCompletedTasks();

        } else if (e.getActionCommand().equals("s")) {
            saveTasks();

        } else if (e.getActionCommand().equals("l")) {
            loadTasks();

        } else if (e.getActionCommand().equals("q")) {
            quit();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a task to list
    public void addTask() {

        playMusic();
//            try {
//
//                String soundName = "StarWars60.wav";
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
//                (new File("./data/StarWars.wav").getAbsoluteFile());
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                clip.start();
//            } catch (Exception eqwe) {
//                System.out.println("Nope");
//            }
        listModel.removeAllElements();
        try {
            log.addTask(new Task(field.getText()));
            notCompleted.addTask(new Task(field.getText()));
            for (int i = 0; i < log.numTasks(); i++) {
                Task b = log.getTaskValue(i);
                add(b.getDescription());


                //System.out.println(b.getDescription());
            }
            field.setText(null);
            area.setText(null);


            List<Task> tasks = log.getTasks();
            List<Task> tasks23 = notCompleted.getTasks();

            for (Task t : tasks) {
                System.out.println(t.getDescription());
            }
            for (Task t1 : tasks23) {
                System.out.println(t1.getDescription());
            }
        } catch (Exception e) {
            area.setText("Enter a proper task");
        }

    }


    // MODIFIES: this
    // EFFECTS: adds a task to the Completed List
    public void markCTask() {
        playMusic();
        if (log.isTask(new Task(field.getText()))) {
            listModel.removeAllElements();
            completed.addTask(new Task(field.getText()));
            int a = notCompleted.getTaskNum(completed.getTaskValue(completed.numTasks() - 1));
            notCompleted.remove(a + 1);
            for (int i = 0; i < completed.numTasks(); i++) {
                Task b1 = completed.getTaskValue(i);
                add(b1.getDescription());
                //System.out.println(b.getDescription());

            }
            field.setText(null);
            area.setText(null);
        } else {
            area.setText("Add the task to the list before marking it as complete!");

        }


    }

    // MODIFIES: this
    // EFFECTS: deletes a task from the list
    public void delTask() {
        playMusic();
        try {
            listModel.removeAllElements();
            int taskNum = Integer.parseInt(field.getText());

            Task a = log.getTaskValue(taskNum);


            if (completed.isTask(a)) {
                int b = completed.getTaskNum(a);
                completed.remove(b + 1);
            } else {
                int c = notCompleted.getTaskNum(a);
                notCompleted.remove(c + 1);
            }
            log.remove(taskNum);
            for (int i = 0; i < log.numTasks(); i++) {
                Task b2 = log.getTaskValue(i);
                add(b2.getDescription());
            }
            field.setText(null);
            area.setText(null);
        } catch (Exception e1) {
            area.setText("Enter an index to delete the value from the list");
        }
    }


    // EFFECTS: Returns list of all tasks
    public void allTasks() {
        playMusic();
        listModel.removeAllElements();
        List<Task> tasks = log.getTasks();

        for (Task t : tasks) {
            add(t.getDescription());
        }
        field.setText(null);
        area.setText(null);
    }

    // EFFECTS: Returns a list of completed tasks
    public void completedTasks() {
        playMusic();
        listModel.removeAllElements();
        if (completed.numTasks() > 0) {
            List<Task> tasksC = completed.getTasks();
            for (Task t : tasksC) {
                add(t.getDescription());
            }
            field.setText(null);
            area.setText(null);

        } else {
            area.setText("You haven't completed any tasks");
        }
    }


    // EFFECTS: Returns a list of not completed Tasks
    public void notCompletedTasks() {
        playMusic();
        listModel.removeAllElements();
        if (notCompleted.numTasks() > 0) {
            List<Task> tasksNc = notCompleted.getTasks();

            for (Task t : tasksNc) {
                add(t.getDescription());
            }
            field.setText(null);
            area.setText(null);
        } else {
            area.setText("Hurray!! You have completed all the tasks");
        }

    }

    // MODIFIES: this
    // EFFECTS: Saves the tasks to a file
    public void saveTasks() {
        playMusic();
        listModel.removeAllElements();
        try {
            log.jsonWriter.open();
            log.jsonWriter.write(log);
            log.jsonWriter.close();
            completed.jsonWriter.open();
            completed.jsonWriter.write(completed);
            completed.jsonWriter.close();
            notCompleted.jsonWriter.open();
            notCompleted.jsonWriter.write(notCompleted);
            notCompleted.jsonWriter.close();
            area.setText("Saved " + log.getName() + " to " + "./data/AllTasks.txt");
            System.out.println("sd");
        } catch (FileNotFoundException var2) {
            area.setText("Unable to write to file: ./data/AllTasks.txt");
        }


    }

    // MODIFIES: this
    // EFFECTS: Loads tasks from the file
    public void loadTasks() {
        playMusic();
        listModel.removeAllElements();
        try {
            log = log.jsonReader.read();
            completed = completed.jsonReader.read();
            notCompleted = notCompleted.jsonReader.read();
            area.setText("Loaded " + log.getName() + " from " + "./data/AllTasks.txt");

            List<Task> tasks = log.getTasks();
            List<Task> tasksC = completed.getTasks();
            List<Task> tasksNc = notCompleted.getTasks();
            init();
            for (Task t : tasks) {
                log.addTask(t);
            }
            for (Task t : tasksC) {
                completed.addTask(t);
            }
            for (Task t : tasksNc) {
                notCompleted.addTask(t);
            }


        } catch (IOException ewe) {
            area.setText("Unable to read from file: " + "./data/AllTasks.txt");
        }

    }

    // MODIFIES: this
    // EFFECTS: Quits the Application
    public void quit() {
        int n = JOptionPane.showConfirmDialog(
                this,
                "Would you like to Save your file?",
                "Save Question",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            saveTasks();
        }
        listModel.removeAllElements();
        field.setText(null);
        area.setText(null);
        System.exit(1);

    }

    public static void main(String[] args) {
        new GuiMain();
    }


}