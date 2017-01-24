package com.lindroid.radiobutton;

/**
 * Created by Lindroid on 2017/2/14.
 */

public class ListItem {
    private String time;
    private String stateName;
    private int isCheck;//0,未选中；1选中

    public ListItem(String time, String stateName, int isCheck) {
        this.time = time;
        this.stateName = stateName;
        this.isCheck = isCheck;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }
}
