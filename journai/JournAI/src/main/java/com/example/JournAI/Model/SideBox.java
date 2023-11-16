package com.example.JournAI.Model;

public class SideBox {

    private String start;
    private String option;
    private String end;


    public SideBox(String start, String option, String end) {
        this.start = start;
        this.option = option;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
