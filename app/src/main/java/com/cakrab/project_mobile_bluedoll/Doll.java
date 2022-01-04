package com.cakrab.project_mobile_bluedoll;

public class Doll {
    private String dollId;
    private String dollName;
    private String dollCreator;
    private String dollDescription;
    private String dollImage;

    public Doll(String dollId, String dollName, String dollCreator, String dollDescription, String dollImage) {
        this.dollId = dollId;
        this.dollName = dollName;
        this.dollCreator = dollCreator;
        this.dollDescription = dollDescription;
        this.dollImage = dollImage;
    }

    public String getDollId() {
        return dollId;
    }

    public void setDollId(String dollId) {
        this.dollId = dollId;
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

    public String getDollImage() {
        return dollImage;
    }

    public void setDollImage(String dollImage) {
        this.dollImage = dollImage;
    }
}
