package com.example.appforbrac.Model;

public class course {


    private String faculty;


    private String key;
    private String name;

    public course(String n, String k, String f)
    {
        faculty= f;
        key = k;
        name = n;
    }
    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
    public String getFaculty() {
        return faculty;
    }
}
