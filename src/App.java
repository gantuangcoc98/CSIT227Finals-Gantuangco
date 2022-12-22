import javax.naming.Name;
import javax.naming.NameAlreadyBoundException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
                try {
                    //NAME INPUT ERRORS
                    try {
                        int number = Integer.parseInt(tfName.getText());
                        JOptionPane.showMessageDialog(btnSave, "Name should not be a number!");
                        tfName.setText("");
                    } catch (NumberFormatException nfe) {
                        if (tfName.getText().isEmpty())
                            throw new NameError();
                    }

                    //AGE ERRORS
                    try {
                        int age = Integer.parseInt(tfAge.getText());
                        if (age == 0 || age < 0) {
                            throw new AgeError();
                        }
                    } catch (NumberFormatException nfe) {
                        if (tfAge.getText().isEmpty())
                            JOptionPane.showMessageDialog(btnSave, "Age should not be empty!");
                        else
                            JOptionPane.showMessageDialog(btnSave, "Age should be a number!");
                        tfAge.setText("");
                    }

                    //MONTHS WORKED ERRORS
                    try {
                        int monthsWorked = Integer.parseInt(tfMonths.getText());
                        if (rbClerk.isSelected() || rbManager.isSelected()) {
                            if (monthsWorked < 0)
                                throw new MonthsWorkedError();
                        }
                    } catch (NumberFormatException nfe) {
                        if (rbClerk.isSelected() || rbManager.isSelected()) {
                            if (tfMonths.getText().isEmpty())
                                JOptionPane.showMessageDialog(btnSave, "Months Worked should not be empty!");
                            else {
                                JOptionPane.showMessageDialog(btnSave, "Months Worked should be a number!");
                                tfMonths.setText("");
                            }
                        }
                    }

                    //SALARY ERRORS
                    try {
                        int salary = Integer.parseInt(tfSalary.getText());
                        if (rbClerk.isSelected() || rbManager.isSelected()) {
                            if (salary < 0)
                                throw new SalaryError();
                        }
                    } catch (NumberFormatException nfe) {
                        if (rbClerk.isSelected() || rbManager.isSelected()) {
                            if (tfSalary.getText().isEmpty())
                                JOptionPane.showMessageDialog(btnSave, "Salary should not be empty!");
                            else {
                                JOptionPane.showMessageDialog(btnSave, "Salary should be a number!");
                                tfSalary.setText("");
                            }
                        }
                    }

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
                catch (NameError ne) {
                    if (tfName.getText().isEmpty())
                        JOptionPane.showMessageDialog(btnSave, "Name should not be empty!");
                }
                catch (AgeError ae) {
                    int num = Integer.parseInt(tfAge.getText());
                    if (num == 0) {
                        JOptionPane.showMessageDialog(btnSave, "Age should not be zero!");
                        tfAge.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(btnSave, "Age should not be less than zero!");
                        tfAge.setText("");
                    }
                }
                catch (MonthsWorkedError mwe) {
                    JOptionPane.showMessageDialog(btnSave, "Months worked should not be less than zero!");
                    tfMonths.setText("");
                }
                catch (SalaryError se) {
                    JOptionPane.showMessageDialog(btnSave, "Salary should not be less than zero!");
                    tfSalary.setText("");
                }
                catch (NumberFormatException nfe) {
                    if (tfAge.getText().isEmpty())
                        new AgeError();
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public class NameError extends Throwable {
        public NameError() {}
    }

    public class AgeError extends Throwable {
        public AgeError() {}
    }

    public class MonthsWorkedError extends Throwable {
        public MonthsWorkedError() {}
    }

    public class SalaryError extends Throwable {
        public SalaryError() {}
    }
}
