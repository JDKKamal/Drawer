package com.jdkgroup.model;

/**
 * Created by kamlesh on 12/18/2016.
 */

public class DrawerItem {

    private int redirect;
    private String title, image, icon_color;

    public DrawerItem(int redirect, String title, String image, String icon_color) {
        this.redirect = redirect;
        this.title = title;
        this.image = image;
        this.icon_color = icon_color;
    }

    public int getRedirect() {
        return redirect;
    }

    public void setRedirect(int redirect) {
        this.redirect = redirect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIcon_color() {
        return icon_color;
    }

    public void setIcon_color(String icon_color) {
        this.icon_color = icon_color;
    }
}
