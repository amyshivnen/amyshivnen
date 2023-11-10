package AddStudentGUI;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

// Define the main class 
public class AddStudentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	// Instance variables for GUI components
    private JTextField nameTextField, addressTextField;
    private JTextArea studentListTextArea, moduleSelectionTextArea;
    private JCheckBox checkBoxModule1, checkBoxModule2, checkBoxModule3;

    private ArrayList<String> studentList;

    // Constructor
    public AddStudentGUI() {
    	studentList = new ArrayList<>();
        // Set up the main frame
        setTitle("Student Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create two panels
        JPanel panelNewStudent = createNewStudentPanel();
        JPanel panelButtons = createButtonsPanel();

        // Add panels to the frame
        add(panelNewStudent, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        // Set frame properties
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void updateStudentListTextArea() {
		// TODO Auto-generated method stub
		 studentListTextArea.setText("");
		    for (String studentInfo : studentList) {
		        studentListTextArea.append(studentInfo + "\n");
		    }
    }
	

    // Create the panel for adding a new student
    private JPanel createNewStudentPanel() {
        
        JPanel panelNewStudent = new JPanel(new BorderLayout());
        panelNewStudent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "New Student", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

        // Create sub-panels for adding a student, displaying student list, and selecting modules
        JPanel panelAddStudent = createAddStudentPanel();
        JPanel panelShowStudents = createShowStudentsPanel();
        JPanel panelModules = createModulesPanel();

        // Add sub-panels to the main panel
        panelNewStudent.add(panelAddStudent, BorderLayout.NORTH);
        panelNewStudent.add(panelShowStudents, BorderLayout.CENTER);
        panelNewStudent.add(panelModules, BorderLayout.EAST);

        return panelNewStudent;
    }

    // Create the panel for adding a student
    private JPanel createAddStudentPanel() {
        // Create a panel with a flow layout
        JPanel panelAddStudent = new JPanel(new FlowLayout());

        // Create text fields for name and address
        nameTextField = new JTextField("Peter Smith", 20);
        addressTextField = new JTextField("35 Liffey Street, Dublin 2", 20);

        // Create buttons for submitting and clearing
        JButton btnSubmit = new JButton("Submit");
        JButton btnClear = new JButton("Clear");

        // Add action listeners to buttons
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Take the data from text fields and add to the student list
                String studentName = nameTextField.getText();
                String studentAddress = addressTextField.getText();
                String studentInfo = studentName + ", " + studentAddress;

                studentList.add(studentInfo);
                updateStudentListTextArea();
            }
        });
        

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear text fields
                nameTextField.setText("");
                addressTextField.setText("");
            }
        });

        // Add components to the panel
        panelAddStudent.add(new JLabel("Name:"));
        panelAddStudent.add(nameTextField);
        panelAddStudent.add(new JLabel("Address:"));
        panelAddStudent.add(addressTextField);
        panelAddStudent.add(btnSubmit);
        panelAddStudent.add(btnClear);

        return panelAddStudent;
    }

    //Create the panel for displaying the student list
    private JPanel createShowStudentsPanel() {
        // Create a panel with a border layout
        JPanel panelShowStudents = new JPanel(new BorderLayout());

        // Create a label and text area for displaying the student list
        JLabel labelStudentList = new JLabel("Student List");
        studentListTextArea = new JTextArea(10, 30);
        studentListTextArea.setEditable(false);

        // Add components to the panel
        panelShowStudents.add(labelStudentList, BorderLayout.NORTH);
        panelShowStudents.add(new JScrollPane(studentListTextArea), BorderLayout.CENTER);

        return panelShowStudents;
    }

    // Method to create the panel for selecting modules
    private JPanel createModulesPanel() {
        // Create a panel with a grid layout
        JPanel panelModules = new JPanel(new GridLayout(2, 1));

        // Sub-panel for check-boxes
        JPanel panelCheckBoxes = new JPanel(new GridLayout(0, 1));
        checkBoxModule1 = new JCheckBox("OOSD");
        checkBoxModule2 = new JCheckBox("Accounting");
        checkBoxModule3 = new JCheckBox("Statistics");

        // Add listeners to check-boxes
        checkBoxModule1.addActionListener(new CheckboxListener());
        checkBoxModule2.addActionListener(new CheckboxListener());
        checkBoxModule3.addActionListener(new CheckboxListener());

        panelCheckBoxes.add(checkBoxModule1);
        panelCheckBoxes.add(checkBoxModule2);
        panelCheckBoxes.add(checkBoxModule3);

        // Sub-panel for module selection text area
        JPanel panelModuleSelection = new JPanel(new BorderLayout());
        moduleSelectionTextArea = new JTextArea(5, 30);
        moduleSelectionTextArea.setEditable(false);
        panelModuleSelection.add(new JScrollPane(moduleSelectionTextArea), BorderLayout.CENTER);

        // Add sub-panels to the main panel
        panelModules.add(panelCheckBoxes);
        panelModules.add(panelModuleSelection);

        return panelModules;
    }

    // Create the panel for buttons
    private JPanel createButtonsPanel() {
        // Create a panel with a flow layout
        JPanel panelButtons = new JPanel(new FlowLayout());

        // Create buttons for finishing and clearing all
        JButton btnFinish = new JButton("Finish");
        JButton btnClearAll = new JButton("Clear All");

        // Add action listeners to buttons
        btnFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
            }
        });
        //Override ActionListener with actionPerformed
        btnClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all selections of the data
                nameTextField.setText("");
                addressTextField.setText("");
                studentList.clear();
                checkBoxModule1.setSelected(false);
                checkBoxModule2.setSelected(false);
                checkBoxModule3.setSelected(false);
                moduleSelectionTextArea.setText("");
                
                updateStudentListTextArea();
            }
        });

        
        panelButtons.add(btnFinish);
        panelButtons.add(btnClearAll);

        return panelButtons;
    }

    // Listener class for check-boxes
    private class CheckboxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Update the module selection text area based on check-box state
            StringBuilder selectedModules = new StringBuilder();

            if (checkBoxModule1.isSelected()) {
                selectedModules.append("OOSD\n");
            }
            if (checkBoxModule2.isSelected()) {
                selectedModules.append("Accounting\n");
            }
            if (checkBoxModule3.isSelected()) {
                selectedModules.append("Statistics\n");
            }

            moduleSelectionTextArea.setText(selectedModules.toString());
        }
    }

    //Method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddStudentGUI();
            }
        });
    }
}



