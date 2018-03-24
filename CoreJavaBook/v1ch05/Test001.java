import equals.Employee;
import equals.Manager;

public class Test001 {

    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        Manager[] managers = new Manager[3];
        employees = managers;
        employees[0] = new Employee("ads", 23D, 1900, 1, 23);
        managers[0].setBonus(234D);
    }

}

