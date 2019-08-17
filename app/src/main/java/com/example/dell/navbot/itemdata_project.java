package com.example.dell.navbot;

public class itemdata_project {
    public  String name;
    public String namelast;
    public String money;
    public String desc;
    public String time;
    public int image;
    public itemdata_project(String name,String namelast,String money,String desc,String time,int image)
    {
        this.name=name;
        this.money=money;
        this.desc=desc;

        this.time=time;
        this.namelast = namelast;
        this.image=image;
    }
}
