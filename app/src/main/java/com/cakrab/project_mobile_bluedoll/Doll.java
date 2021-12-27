package com.cakrab.project_mobile_bluedoll;

public class Doll {
    private String dollImage;
    private String dollName;
    private String dollCreator;
    private String dollDescription;

    public Doll(String dollImage, String dollName, String dollCreator, String dollDescription) {
        this.dollImage = dollImage;
        this.dollName = dollName;
        this.dollCreator = dollCreator;
        this.dollDescription = dollDescription;
    }

    public String getDollImage() {
        return dollImage;
    }

    public void setDollImage(String dollImage) {
        this.dollImage = dollImage;
    }

    public String getDollName() {
        return dollName;
    }

    public void setDollName(String dollName) {
        this.dollName = dollName;
    }

    public String getDollCreator() {
        return dollCreator;
    }

    public void setDollCreator(String dollCreator) {
        this.dollCreator = dollCreator;
    }

    public String getDollDescription() {
        return dollDescription;
    }

    public void setDollDescription(String dollDescription) {
        this.dollDescription = dollDescription;
    }
}
