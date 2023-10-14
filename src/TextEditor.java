import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    JFrame window;
    JMenuBar menuBar;

    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;

    JMenuItem copy, paste, cut, selectAll, close;

    JTextArea textArea;

    TextEditor(){
        //Initialization of window(Frame)
        window = new JFrame("Text Editor");

        //Initialization of menuBar
        menuBar = new JMenuBar();

        //Initialization of Text Area
        textArea = new JTextArea();

        //Initialization of File Menu
        file = new JMenu("File");

        //Initialization of Edit Menu
        edit = new JMenu("Edit");

        //Initialization of File MenuItems
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Adding ActionListener to File MenuItems
        //We need to make the action Listener class to listen where the action event occurs
        //Adding ActionListener to all the places where ActionEvent Occurs and perform particular function
        newFile.addActionListener(this); // "this" refers to object of textEditor class, textEditor behaving like ActionListener
        openFile.addActionListener(this);
        saveFile.addActionListener(this);



        //Adding file MenuItems to file Menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialization of Edit MenuItems
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        cut = new JMenuItem("Cut");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Adding ActionListener to edit MenuItems
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding edit MenuItems to edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Adding Menus to MenuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Adding MenuBar to Window(Frame)
        window.setJMenuBar(menuBar);

        //Create Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0 , 0));
        //Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add Scroll Pane to panel
        panel.add(scrollPane);
        //Add Panel to window
        window.add(panel);

        window.setBounds(0, 0, 1000, 600);
        window.setVisible(true);
        window.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            //Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If the user clicked on Open Button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selected File
                File file = fileChooser.getSelectedFile();
                //Getting path of selected file
                String filePath = file.getPath();
                try{
                    //Initialization of file Reader
                    FileReader fileReader  = new FileReader(filePath);
                    //Initialization of Buffer Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = ""; //Holding content of current line
                    String output = ""; //Pasting complete text

                    //Read contents line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    //Set output string to text area
                    textArea.setText(output);
                } catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            //if user clicked on save Button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile() + ".txt");

                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch(IOException ioException){
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