package ui;

import model.Task;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Todo extends JPanel implements ListSelectionListener {

    private JButton addToList;
    private JButton deleteFromList;
    private JButton allTasks;
    private JButton completedTasks;
    private JButton notCompletedTasks;
    private JButton markAsCompleted;
    private JButton save;
    private JButton load;
    private JButton closeApp;
    public JTextField field;

    private JList list;


    private WorkRoom log;
    private WorkRoom completed;
    private WorkRoom notCompleted;
    private DefaultListModel dx;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public Todo() {
        init();
    }

        // MODIFIES: this
        // EFFECTS: processes user input
//    private void runTodoList() {
//        boolean keepGoing = true;
//        String scanner = null;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            scanner = input.next();
//            scanner = scanner.toLowerCase();
//
//            if (scanner.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(scanner);
//            }
//        }
//
//        System.out.println("\nGoodbye!");
//    }

    private void init() {
        log = new WorkRoom("All Tasks workroom");
        dx = new DefaultListModel();
        log.jsonWriter = new JsonWriter("./data/AllTasks.txt");
        log.jsonReader = new JsonReader("./data/AllTasks.txt");
        completed = new WorkRoom("Completed Tasks workroom");
        completed.jsonWriter = new JsonWriter("./data/CompletedTasks.txt");
        completed.jsonReader = new JsonReader("./data/CompletedTasks.txt");
        notCompleted = new WorkRoom("Not-Completed Tasks workroom");
        notCompleted.jsonWriter = new JsonWriter("./data/Not-CompletedTasks.txt");
        notCompleted.jsonReader = new JsonReader("./data/Not-CompletedTasks.txt");

        //Create the list and put it in a scroll pane.
        list = new JList(dx);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addToList = new JButton("Add to list");
        Todo.AddToList addToList1 = new Todo.AddToList(addToList);
        addToList.setActionCommand("Add to list");
        addToList.addActionListener(addToList1);
        addToList.setEnabled(false);

        JButton deleteFromList = new JButton("Delete From List");
        //DeleteFromList deleteFromList1 = new DeleteFromList();
        deleteFromList = new JButton("Delete from list");
        deleteFromList.setActionCommand("Delete from list");
        deleteFromList.addActionListener(new Todo.DeleteFromList());

        field = new JTextField(10);
        field.addActionListener(addToList1);
        field.getDocument().addDocumentListener(addToList1);
        String name = log.getTaskValue(
                list.getSelectedIndex() + 1).toString();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(deleteFromList);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(field);
        buttonPane.add(addToList);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }




    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    private class DeleteFromList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            log.remove(index);

            int size = log.numTasks();

            if (size == 0) { //Nobody's left, disable firing.
                deleteFromList.setEnabled(false);

            } else { //Select an index.
                if (index == log.numTasks()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }



        }



    }



    private class AddToList implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddToList(JButton addToList) {
            this.button = addToList;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            String name = field.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                field.requestFocusInWindow();
                field.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            log.addTask(new Task(field.getText()));
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            field.requestFocusInWindow();
            field.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);

        }
        //Required by DocumentListener.

        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }
        //Required by DocumentListener.

        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }

        private boolean alreadyInList(String name) {
            return log.isTask(name);

        }
    }







    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                deleteFromList.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                deleteFromList.setEnabled(true);
            }
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ListDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ui.TodoList();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }






}
