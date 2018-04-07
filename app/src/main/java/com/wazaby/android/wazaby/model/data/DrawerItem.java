package com.wazaby.android.wazaby.model.data;

/**
 * Created by bossmaleo on 02/11/17.
 */

public class DrawerItem {

    private String title;
    private int imageID;

    public DrawerItem(){}

    public DrawerItem(String title,int imageID)
    {
        this.title = title;
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
