import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // declaring properties of text editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;

    TextEditor(){
        //initialize a frame
        frame = new JFrame();

        // initialize menuBar
        menuBar = new JMenuBar();

        // initialize text area
        textArea = new JTextArea();

        // initialize menu's
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize file menu item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // adding actionListener to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        // adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initialize edit menu item
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // adding actionListener to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding menu's item to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // adding menu's to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        // set menu Bar to frame
        frame.setJMenuBar(menuBar);
        // create content panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        // create scroll pane for text area scrollable
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scrollPane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
//        // add text area to frame
//        frame.add(textArea);

        // set dimension of frame
        frame.setBounds(35,0,1400,900);
        // x is for window starting from left corner when x = 0
        // y is for window starting from top when y = 0
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            //perform cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            //perform copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            //perform paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            //perform selectAll operation
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            //perform close operation
            System.exit(0);
        }
        if (actionEvent.getSource() == openFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("Download");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                // try & catch used for exception handling
                try {

                    FileReader fileReader = new FileReader(filePath);//initialize for file read
                    //initialized buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read content of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    // set the output string to the text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            //initialize file picker
            JFileChooser fileChooser = new JFileChooser("Download");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check we click on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new file with chosen directory path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write contents of text area
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();

                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }    
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}