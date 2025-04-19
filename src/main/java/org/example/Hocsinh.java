package org.example;

import org.checkerframework.checker.fenum.qual.Fenum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Hocsinh {
    private String id;
    private String name;
    private int age;
    private double score;
    public Hocsinh(String name, int age, double studentMark) {
        /// random student id with prefix "TVN-AK48-"

        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        this.id = "TVN-AK48-" + randomNumber;
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = studentMark;
    }
    public static void main(String[] args) {
        Hocsinh hocsinh = new Hocsinh("Truong", 20, 9.5);

        System.out.println("Name: " + hocsinh.name);
        System.out.println("Age: " + hocsinh.age);
        System.out.println("Student mark: " + hocsinh.score);
        List<Hocsinh> classroom = new ArrayList<Hocsinh>();
        /// tao danh sach hoc sinh voi 10 ban
        /// random scrore from 1 to 10 in double
        /// random age from 18 to 25 in int
        /// random name from array {"Huong", "Anh", "Mai", "Doti", "Hoa", "Nhu", "Hong", "Khanh", "Tien", "Phuong"}
        for (int i = 0; i < 10; i++) {
            classroom.add(new Hocsinh(radomName(), randomAge(), randomScore()));
        }

        //in ra thong tin cua cac hoc sinh xuat sac

    }
    public void info() {
        System.out.println("Student id: " + id);
        System.out.println("Student name: " + name);

         System.out.println("Student age: " + age);
        System.out.println("Student score: " + score);
    }
    public  Rating rate() {
        enum Rate {
            Yeu,
            TrungBinh,
            Kha,
            Gioi,
            XuatSac
        }
        if (this.score<0 || this.score>10) {
            throw new IllegalArgumentException("Score must be between 0 and 10");
        }
        if (this.score < 5) {
            return Rating.YEU;
        } else if (this.score < 6.5) {
            return Rating.TRUNG_BINH;
        } else if (this.score < 8) {
            return Rating.KHA;
        } else if (this.score < 9) {
            return Rating.GIOI;
        } else {
            return Rating.XUAT_SAC;
        }
    }
    public static String radomName() {
        String[] names = new String[]{"Huong", "Anh", "Mai", "Doti", "Hoa", "Nhu", "Hong", "Khanh", "Tien", "Phuong"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }
    public static int randomAge() {
        Random random = new Random();
        return random.nextInt(25 - 18 + 1) + 18;
    }
    public static
    double randomScore() {
        Random random = new Random();
        double scrore = 1.0 + 9.0 * random.nextDouble();
        return Math.round(scrore * 10.0) / 10.0;
        
    }

}
