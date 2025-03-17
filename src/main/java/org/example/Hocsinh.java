package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Hocsinh {
    private int id;
    private String name;
    private int age;
    private double score;
    public Hocsinh(String name, int age, double studentMark) {
        Random random = new Random();
//        this.id ="TVN-AK48"+ 1000 +random.nextInt(9000);
        this.name = name;
        this.age = age;
        this.score = studentMark;
    }
    public static void main(String[] args) {
        Hocsinh hocsinh = new Hocsinh("Truong", 20, 9.5);
        System.out.println("Name: " + hocsinh.name);
        System.out.println("Age: " + hocsinh.age);
        System.out.println("Student mark: " + hocsinh.score);
        List<Hocsinh> classroom = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            classroom.add(new Hocsinh("Truong", 20, 9.5));
        }
    }
    public  String rating () {
        if (this.score<0 || this.score>10) {
            throw new IllegalArgumentException("Score must be between 0 and 10");
        }
        if (this.score < 5) {
            return "Yeu";
        } else if (this.score < 6.5) {
            return "Trung binh";
        } else if (this.score < 8) {
            return "Kha";
        } else if (this.score < 9) {
            return "Gioi";
        } else {
            return "Xuat sac";
        }
    }
    public static String radomName(){
        String[] names = new String[]{"Huong", "Anh", "Mai", "Doti", "Hoa", "Nhu", "Hong", "Khanh", "Tien", "Phuong"};
        int index = (int) (Math.random() * names.length);
        return names[index];
    }

}
