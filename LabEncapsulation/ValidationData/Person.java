package ValidationData;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;
    private double increaseSalary;

    public Person(String firstName, String lastName, int age, double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);
    }


    public void increaseSalary(double bonus) {
        if (age < 30) {
            bonus /= 2;
            salary =  (salary * (bonus / 100)) + salary;
        } else {
            salary =  (salary * (bonus / 100)) + salary;
        }
    }

    public void setFirstName(String firstName) {
        if (firstName.length() < 3) {
            throw new IllegalStateException("First name cannot be less than 3 symbols");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() < 3) {
            throw new IllegalStateException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalStateException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public void setSalary(double salary) {
        if (salary < 460) {
            throw new IllegalStateException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public double getIncreaseSalary() {
        return increaseSalary;
    }

    public void setIncreaseSalary(double increaseSalary) {
        this.increaseSalary = increaseSalary;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.###");

        return String.format("%s %s gets %f leva",firstName, lastName,salary);
    }
}
