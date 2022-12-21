public class Clerk extends Employee {

    public Clerk(String name, int age, int months_worked, double salary) {
        super(name, age, months_worked, salary);
    }

    @Override
    public String toString() {
        return "How may I help you?";
    }
}
