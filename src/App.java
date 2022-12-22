import javax.naming.Name;
import javax.naming.NameAlreadyBoundException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

        btnSave.addActionListener(new ActionListener() {
            int count = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //NO ROLE SELECTED ERROR
                    if (rbCustomer.isSelected() == false && rbClerk.isSelected() == false && rbManager.isSelected() == false)
                        throw new NoRoleSelected();

                    //NO INPUT GIVEN ERROR (ALL TEXT FIELD)
                    if (tfName.getText().isEmpty() && tfAge.getText().isEmpty() && tfMonths.getText().isEmpty() && tfSalary.getText().isEmpty())
                        throw new NoInputGiven();

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
                }
                catch (NoInputGiven nig) {
                    JOptionPane.showMessageDialog(btnSave, "Please provide input!");
                }
                catch (NoRoleSelected nrs) {
                    JOptionPane.showMessageDialog(btnSave, "Please select a role. (i.e Customer, Clerk, Manager)");
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
                tfLoad.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfName.setText("");
                tfSalary.setText("");
                taPersons.setText("");
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            int index;
            Person load;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    index = Integer.parseInt(tfLoad.getText());
                    load = persons.get(index - 1);
                    tfName.setText(load.name);
                    tfAge.setText(String.valueOf(load.age));
                    if (load instanceof Employee) {
                        tfMonths.setText(String.valueOf(((Employee) load).getMonths_worked()));
                        tfSalary.setText(String.valueOf(((Employee) load).getSalary()));
                        if (load instanceof Clerk)
                            rbClerk.setSelected(true);
                        else
                            rbManager.setSelected(true);
                    }
                    else
                        rbCustomer.setSelected(true);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(btnLoad, "Load input should be a number!");
                } catch (IndexOutOfBoundsException iob) {
                    JOptionPane.showMessageDialog(btnLoad, "Cannot find the person in the list!");
                }
            }
        });

        btnSayHi.addActionListener(new ActionListener() {
            String output = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                if (persons.isEmpty())
                    JOptionPane.showMessageDialog(btnSayHi, "There is no person in the list.");
                for (Person p : persons) {
                    output += p.toString() + "\n";
                }
                taPersons.setText(output);
            }
        });

        btnReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        rbCustomer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rbCustomer.isSelected()) {
                    tfMonths.setText("");
                    tfMonths.setEditable(false);
                    tfSalary.setText("");
                    tfSalary.setEditable(false);
                } else {
                    tfMonths.setEditable(true);
                    tfSalary.setEditable(true);
                }
            }
        });

        // IN THE SAVE BUTTON, I JUST IMPLEMENTED A STRING ARRAY TO STORE THE LIST INSTEAD OF AN ACTUAL FILE
        final String[] output = new String[1];
        btnSavePerson.addActionListener(new ActionListener() {
            String list = "";
            int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Person p : persons) {
                    if (p instanceof Customer) {
                        list += count + ". Customer - " + p.name + " (" + p.age + ")\n";
                        count++;
                    }
                    else if (p instanceof Clerk) {
                        list += count + ". Clerk - " + p.name + " (" + p.age + ")\n";
                        count++;
                    }
                    else if (p instanceof Manager) {
                        list += count + ". Manager - " + p.name + " (" + p.age + ")\n";
                        count++;
                    }
                }
                output[0] = list;
            }
        });

        // IN THE LOAD BUTTON, I JUST LOADED THE LIST THAT IS SAVED FROM THE STRING VARIABLE ABOVE
        btnLoadPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taPersons.setText(String.valueOf(output[0]));
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

    public class NoInputGiven extends Throwable {
        public NoInputGiven() {}
    }

    public class NoRoleSelected extends Throwable {
        public NoRoleSelected() {}
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
