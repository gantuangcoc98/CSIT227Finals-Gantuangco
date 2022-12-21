import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {
    private JPanel pnlMain;
    private JRadioButton rbCustomer;
    private JRadioButton rbClerk;
    private JRadioButton rbManager;
    private JTextField tfName;
    private JTextArea taPersons;
    private JButton btnSave;
    private JTextField tfAge;
    private JTextField tfMonths;
    private JTextField tfSalary;
    private JButton btnClear;
    private JTextField tfLoad;
    private JButton btnLoad;
    private JButton btnSayHi;
    private JButton btnSavePerson;
    private JButton btnLoadPerson;
    private JButton btnReward;

    private List<Person> persons;

    public App() {
        persons = new ArrayList<>();
        // TODO add implementations for all milestones here

        //RadioButton Group
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbCustomer);
        bg.add(rbClerk);
        bg.add(rbManager);

        //Instantiation of Objects
        String list;
        btnSave.addActionListener(new ActionListener() {
            int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbCustomer.isSelected()) {
                    Customer c = new Customer(tfName.getText(), Integer.parseInt(tfAge.getText()));
                    taPersons.setText(count + ". Customer - " + c.name + " (" + c.age + ")");
                    persons.add(c);
                    count++;
                } else if (rbClerk.isSelected()) {
                    Clerk cl = new Clerk(tfName.getText(), Integer.parseInt(tfAge.getText()), Integer.parseInt(tfMonths.getText()), (double) Integer.parseInt(tfSalary.getText()));
                    taPersons.setText(count + ". Clerk - " + cl.name + " (" + cl.age + ")");
                    persons.add(cl);
                    count++;
                } else if (rbManager.isSelected()) {
                    Manager m = new Manager(tfName.getText(), Integer.parseInt(tfAge.getText()), Integer.parseInt(tfMonths.getText()), (double) Integer.parseInt(tfSalary.getText()));
                    taPersons.setText(count + ". Manager - " + m.name + " (" + m.age + ")");
                    persons.add(m);
                    count++;
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbClerk.isSelected())
                    rbCustomer.setEnabled(false);
                else if (rbClerk.isSelected())
                    rbClerk.setEnabled(false);
                else if (rbClerk.isSelected())
                    rbManager.setEnabled(false);

                tfLoad.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfName.setText("");
                tfSalary.setText("");
                taPersons.setText("");
            }
        });
    }

    public static void main(String[] args) {
        // add here how to make GUI visible
        App app = new App();
        app.setContentPane(app.pnlMain);
        app.setSize(800, 700);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    static void giveReward(int n) {

    }
}
