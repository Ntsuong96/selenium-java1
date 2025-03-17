package org.example;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.reflect.Array.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        System.out.printf(changeCharacter("truong@em@xinh@dep"));
//        System.out.printf(getLastFourCharacter("truongemxinhdep"));
 //       String str= "swiss";
//        findNonRepeatCharacter(str);
//        findLargestDifference(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
          int[] arr = new int[]{34, 1, 23, 4, 3, 12, 45, 33, 6, 22};
          System.out.println("Max item is " + findMaxItem(arr));
//        findLargestNumber(arr);
//        System.out.println("largest number is " + largestNumber(10, 20, 30));
//        System.out.printf(capitalizeFirstLetter("truong    em xinh dep"));
//        for (int i = 1; i <= 10; i++) {
//            System.out.println("Bảng cửu chương " + i);
//            for (int j = 1; j <= 10; j++) {
//                System.out.printf("%d x %d = %d\n", i, j, i * j);
//            }
//        }
// FindMaxValue
            int[] arr1 ={34,1,23,3,45,5,22,11,2};
            int max =Arrays.stream(arr).max().getAsInt();
            System.out.println("Max item is " + max);
            Arrays.stream(arr1).sum();
            System.out.println("Sum of array is " + Arrays.stream(arr1).sum());

    }

    public static String checkLeepYear (int year)
    {
        if (year < 0)
        {
            throw new IllegalArgumentException("Year cannot be negative");
        }
    String flag = "Leep year";
    if (year % 4 == 0)
    {
        if (year % 100 == 0)
        {
            if (year % 400 == 0)
            {
                flag = "Leep year";
            }
            else
            {
                flag = "Not Leep year";
            }
        }
        else
        {
            flag = "Leep year";
        }
    }
    else
    {
        flag = "Not Leep year";
    }
    return flag;
    }

    public static float calculateTaxiPrice (int km)
    {
        if (km <= 0)
        {
            throw new IllegalArgumentException("Km cannot be negative");
        }
        float price = 0;
        if (km <= 1)
        {
            price = 13000;
        }
        else if (km <= 10)
        {
            price = 13000 + (km - 1) * 10000;
        }
        else if (km >10)
        {
            price = 13000 + 9 * 10000 + (km - 10) * 8000;
        }
        return price;
    }

    public static float largestNumber(float a, float b, float c) {
        float max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        return max;
    }

    public static String capitalizeFirstLetter(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            throw new IllegalArgumentException("Sentence cannot be null or empty");
        }
        String[] words = sentence.trim().split("\\s+"); //// importtant ̣khi co nhieu doan trang ̣regex
        StringBuilder capitalizedSentence = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                capitalizedSentence.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitalizedSentence.toString().trim();
    }

/// Thay đổi chuỗi kí tự cho trước (thay đổi kí tự @ thành _)
    public static String changeCharacter (String str){
    if (str.isEmpty()||str ==null){
        throw new IllegalArgumentException("String can not be null");
        }
    return str.replace("@","_");
    }

//// Lấy 4 kí tự cuối cùng của 1 chuỗi cho trước
    public static String getLastFourCharacter (String str) {
        if (str.isEmpty() || str == null) {
            throw new IllegalArgumentException("String can not be null");
        }
        if (str.length() < 4) {
            throw new IllegalArgumentException("String must have at least 4 characters");
        }
        int size = str.length();
        int counter = size - 4;
//        for (int i = counter; i < size; i++) {
//            System.out.print(str.charAt(i));
//
//        }
        return str.substring(counter);

    }

    /// Tìm kí tự không lặp lại đầu tiên của 1 chuỗi
    public static void findNonRepeatCharacter (String str){
        Map<Character, Integer> map = new LinkedHashMap<>();
        int counter =1;
        for (int i= 0; i<str.length(); i ++){
            if(map.containsKey(str.charAt(i))){
                map.put(str.charAt(i), map.get(str.charAt(i))+1);
            }
            else {
                map.put(str.charAt(i), counter);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue() == 1){
                System.out.println("First non-repeating character is " + entry.getKey());
                break;
            }
        }
    }

    /// /Tìm số lớn nhất và lớn thứ 2 của 1 mảng
    public static void findLargestNumber (int[] arr){
        if (arr==null || arr.length==0){
            throw new IllegalArgumentException("Array can not be null or empty");
        }
        int lengthOfArray = arr.length;
        int largestNumber = arr[0];
        int smallestNumber = arr[0];
        int secondLargestNumber = arr[0];
        for (int i=1; i<lengthOfArray; i++){
            if(arr[i]> largestNumber){
                secondLargestNumber =largestNumber;
                largestNumber = arr[i];
            }

        }
        System.out.println("Largest number is "+largestNumber + " Second largest number is " + secondLargestNumber);
    }

    /// Tìm độ lệch lớn nhất giữa 2 phần tử trong một mảng
    public static void findLargestDifference (int[] arr){
        if (arr==null || arr.length==0){
            throw new IllegalArgumentException("Array can not be null or empty");
        }
        int lengthOfArray = arr.length;
        int max = arr[0];
        int min = arr[0];
        for (int i=1; i<lengthOfArray; i++){
            if (arr[i]>max){
                max = arr[i];
            }
            else if (arr[i]<min){
                min = arr[i];
            }
        }
        System.out.println("Largest difference is:" + (max-min));


    }

    /// Find max item
    public static int findMaxItem(int[] arr) {
        if (arr.length == 0 || arr == null) {
            throw new IllegalArgumentException("Array can not be null or empty");
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;




    }

   ////Tinh tong cua 1 mang so nguyen cho truoc
    public static void sumOfArray(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        System.out.println("Sum of array is " + sum);
//        int min = Arrays.stream(arr).min(Comparator.naturalOrder().getClass();
    }
}
