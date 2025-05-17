package demo;

import org.testng.annotations.*;

public class SampleTest1 {
    @BeforeSuite
    void beforeSuite() {
        System.out.println("Before Suite");
    }
    @BeforeTest
    void beforeTest() {
        System.out.println("Before Test");
    }
    @BeforeClass
    void beforeClass() {
        System.out.println("Before Class");
    }
    @BeforeMethod
    void beforeMethod() {
        System.out.println("Before Method");
    }
    @Test
    void test1() {
        System.out.println("Test 1");
    }
    @Test
    void test2() {
        System.out.println("Test 2");
    }
    @Test
    void test3() {
        System.out.println("Test 2");
    }
    @Test
    void test4() {
        System.out.println("Test 2");
    }
    @Test
    void test5() {
        System.out.println("Test 2");
    }
    @AfterMethod
    void afterMethod() {
        System.out.println("After Method");
    }
    @AfterClass
    void afterClass() {
        System.out.println("After Class");
    }
    @AfterTest
    void afterTest() {
        System.out.println("After Test");
    }
    @AfterSuite
    void afterSuite() {
        System.out.println("After Suite");
    }

}

