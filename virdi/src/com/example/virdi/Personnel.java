package com.example.virdi;

public class Personnel {
	private int id;
	private String name;
	private String gname;
	public Personnel(){}
	public Personnel(int id,String name,String gname){
		super();
		this.gname = gname;
		this.name = name;
		this.id = id;
	}
	public int getID(){
        return this.id;
    }
	public void setID(int id){
		this.id = id;
	}
	public String getName(){
        return this.name;
    }
     
    // setting name
    public void setName(String name){
        this.name = name;
    }
    public String getGName(){
        return this.gname;
    }
     
    // setting name
    public void setGName(String gname){
        this.gname = gname;
    }

}
