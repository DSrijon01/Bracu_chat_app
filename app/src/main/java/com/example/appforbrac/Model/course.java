package com.example.appforbrac.Model;

public class course {


    private String faculty;


    private int key;
    private String name;

    public course(String faculty, int key, String name)
    {
        this.faculty= faculty;
        this.key = key;
        this.name = name;
    }
    public course()
    {

    }
    public int getKey() {
       return key;
    }

    public String getName() {
        return name;
    }
    public String getFaculty() {
        return faculty;
    }
}
