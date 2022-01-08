package NUS.edu.Workshop12.model;

import java.io.Serializable;

public class Generate implements Serializable{
    private int numberVal;
    private String Greetings;
    private int maxNum;
    private String maxText;
    
    public void setMaxNum(int i){
        this.maxNum=i;
        this.maxText="Please enter an integer up to "+ this.getMaxNum().toString();
    }
    public Integer getMaxNum(){
        return this.maxNum;
    }
    public String getMaxText(){
        return this.maxText;
    }

    public void setGreetings(String s){
        this.Greetings=s;
    }
    public void setNumberVal(int numberVal){
        this.numberVal=numberVal;
    }
    public int getNumberVal(){
        return this.numberVal;
    }
    public String getGreetings(){
        return this.Greetings;
    }

}
