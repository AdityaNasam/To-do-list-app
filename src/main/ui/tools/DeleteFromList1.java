package ui.tools;

public class DeleteFromList1  {
//    private Task task;
//
//    public DeleteFromList1(TodoList editor, JComponent parent) {
//        super(editor, parent);
//        task = null;
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  constructs a delete button which is then added to the JComponent (parent)
//    //           which is passed in as a parameter
//    @Override
//    protected void createButton(JComponent parent) {
//        button = new JButton("Delete From List");
//        addToParent(parent);
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  constructs a new listener object which is added to the JButton
//    @Override
//    protected void addListener() {
//        button.addActionListener(new DeleteToolClickHandler());
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  Sets the shape at the current mouse position as the shape to delete,
//    //           selects the shape and plays it
//    @Override
//    public void mousePressedInDrawingArea(MouseEvent e) {
//        task = editor.getShapeInDrawing(e.getPoint());
//        if (task != null) {
//            task.selectAndPlay();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  unselects the shape being deleted, and removes it from the drawing
//    @Override
//    public void mouseReleasedInDrawingArea(MouseEvent e) {
//        if (task != null) {
//            task.unselectAndStopPlaying();
//            editor.removeFromDrawing(task);
//            task = null;
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  Sets the shape at the current mouse position as the shape to delete,
//    //           selects the shape and plays it
//    @Override
//    public void mouseDraggedInDrawingArea(MouseEvent e) {
//        task = editor.getShapeInDrawing(e.getPoint());
//        if (task != null) {
//            task.selectAndPlay();
//        }
//    }
//
//    private class DeleteToolClickHandler implements ActionListener {
//
//        // EFFECTS: sets active tool to the delete tool
//        //          called by the framework when the tool is clicked
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            editor.setActiveTool(DeleteTool.this);
//        }
//    }
}
