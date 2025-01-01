package com.galatea.frontend.presentation.model;

import com.galatea.frontend.utils.Constants;

public class ProjectData {
    private String name;
    private String path;
    private String type;
    private String angularVersion;
    private String bdsVersion;
    private String featurePath;

    public ProjectData() {
        name = "";
        type = Constants.ANGULAR_WIDGET;
        angularVersion = Constants.ANGULAR_VERSION_16;
        bdsVersion = Constants.BDS_VERSION_13;
        path = "";
        featurePath = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAngularVersion() {
        return angularVersion;
    }

    public void setAngularVersion(String angularVersion) {
        this.angularVersion = angularVersion;
    }

    public String getBdsVersion() {
        return bdsVersion;
    }

    public void setBdsVersion(String bdsVersion) {
        this.bdsVersion = bdsVersion;
    }

    public String getFeaturePath() {
        return featurePath;
    }

    public void setFeaturePath(String featurePath) {
        this.featurePath = featurePath;
    }

    @Override
    public String toString() {
        return "ProjectData{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", angularVersion='" + angularVersion + '\'' +
                ", bdsVersion='" + bdsVersion + '\'' +
                ", path='" + path + '\'' +
                ", featurePath='" + featurePath + '\'' +
                '}';
    }
}
