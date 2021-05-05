package ui.tools;

import model.Task;

public class AddToList1  {

    protected Task task;

//    public AddToList1(TodoList editor, JComponent parent) {
//        super(editor, parent);
//        task = null;
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS:  creates new button and adds to parent
//    @Override
//    protected void createButton(JComponent parent) {
//        button = new JButton(getLabel());
//        button = customizeButton(button);
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  associate button with new ClickHandler
//    @Override
//    protected void addListener() {
//        button.addActionListener(new ShapeToolClickHandler());
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  a shape is instantiate MouseEvent occurs, and played and
//    //           added to the editor's drawing
//    @Override
//    public void mousePressedInDrawingArea(MouseEvent e) {
//        makeShape(e);
//        tasks.selectAndPlay();
//        tasks.setBounds(e.getPoint());
//        editor.addToDrawing(tasks);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS:  unselects this shape, and sets it to null
//    @Override
//    public void mouseReleasedInDrawingArea(MouseEvent e) {
//        tasks.unselectAndStopPlaying();
//        tasks = null;
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  sets the bounds of thes shape to where the mouse is dragged to
//    @Override
//    public void mouseDraggedInDrawingArea(MouseEvent e) {
//        tasks.setBounds(e.getPoint());
//    }
//
//    //EFFECTS: Returns the string for the label.
//    //protected abstract String getLabel();
////    {
////		return "Shape";
////	}
//
//    //EFFECTS: Constructs and returns the new shape
//    //protected abstract void makeShape(MouseEvent e) ;
////	{
////		shape = new Rectangle(e.getPoint(), editor.getMidiSynth());
////	}
//
//    private class ShapeToolClickHandler implements ActionListener {
//
//        // EFFECTS: sets active tool to the shape tool
//        //          called by the framework when the tool is clicked
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            editor.setActiveTool(ShapeTool.this);
//        }
//    }
}
