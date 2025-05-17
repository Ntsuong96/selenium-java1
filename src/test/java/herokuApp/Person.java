package herokuApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Person {
    private String firstName, lastName;
    private double due;


    public Person(String lastName, String firstName, double due) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.due = due;
    }

    public void info() {
        System.out.printf("First name: %s, last name: %s, due: %2f%n " , firstName, lastName, due);
    }
    public double getDue() {
        return due;
    }

    public String getFullName() {
        return String.format("%s %s", lastName, firstName);
    }
}
