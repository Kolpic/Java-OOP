package SalaryIncrease;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;
    private double increaseSalary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }


    public void increaseSalary(double bonus) {
        if (age < 30) {
            bonus /= 2;
            salary =  (salary * (bonus / 100)) + salary;
        } else {
            salary =  (salary * (bonus / 100)) + salary;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
