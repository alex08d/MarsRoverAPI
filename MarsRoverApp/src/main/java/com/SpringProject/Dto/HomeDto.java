package com.SpringProject.Dto;


import javax.persistence.*;

@Entity
@Table(name = "mars_api_preferences")
public class HomeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length = 20)
    private String marsApiRoverData;
    private Integer marsSol;
    private boolean cameraFhaz;
    private boolean cameraRhaz;
    private boolean cameraMast;
    private boolean cameraChemcam;
    private boolean cameraMahli;
    private boolean cameraMardi;
    private boolean cameraNavcam;
    private boolean cameraPancam;
    private boolean cameraMinites;
    private boolean rememberPreferences;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isRememberPreferences() {
        return rememberPreferences;
    }

    public void setRememberPreferences(boolean rememberPreferences) {
        this.rememberPreferences = rememberPreferences;
    }

    public String getMarsApiRoverData() {
        return marsApiRoverData;
    }

    public void setMarsApiRoverData(String marsApiRoverData) {
        this.marsApiRoverData = marsApiRoverData;
    }

    public Integer getMarsSol() {
        return marsSol;
    }

    public void setMarsSol(Integer marsSol) {
        this.marsSol = marsSol;
    }

    public boolean isCameraFhaz() {
        return cameraFhaz;
    }

    public void setCameraFhaz(boolean cameraFhaz) {
        this.cameraFhaz = cameraFhaz;
    }

    public boolean isCameraRhaz() {
        return cameraRhaz;
    }

    public void setCameraRhaz(boolean cameraRhaz) {
        this.cameraRhaz = cameraRhaz;
    }

    public boolean isCameraMast() {
        return cameraMast;
    }

    public void setCameraMast(boolean cameraMast) {
        this.cameraMast = cameraMast;
    }

    public boolean isCameraChemcam() {
        return cameraChemcam;
    }

    public void setCameraChemcam(boolean cameraChemcam) {
        this.cameraChemcam = cameraChemcam;
    }

    public boolean isCameraMahli() {
        return cameraMahli;
    }

    public void setCameraMahli(boolean cameraMahli) {
        this.cameraMahli = cameraMahli;
    }

    public boolean isCameraMardi() {
        return cameraMardi;
    }

    public void setCameraMardi(boolean cameraMardi) {
        this.cameraMardi = cameraMardi;
    }

    public boolean isCameraNavcam() {
        return cameraNavcam;
    }

    public void setCameraNavcam(boolean cameraNavcam) {
        this.cameraNavcam = cameraNavcam;
    }

    public boolean isCameraPancam() {
        return cameraPancam;
    }

    public void setCameraPancam(boolean cameraPancam) {
        this.cameraPancam = cameraPancam;
    }

    public boolean isCameraMinites() {
        return cameraMinites;
    }

    public void setCameraMinites(boolean cameraMinites) {
        this.cameraMinites = cameraMinites;
    }

    @Override
    public String toString() {
        return "HomeDto{" +
                "userId=" + userId +
                ", marsApiRoverData='" + marsApiRoverData + '\'' +
                ", marsSol=" + marsSol +
                ", cameraFhaz=" + cameraFhaz +
                ", cameraRhaz=" + cameraRhaz +
                ", cameraMast=" + cameraMast +
                ", cameraChemcam=" + cameraChemcam +
                ", cameraMahli=" + cameraMahli +
                ", cameraMardi=" + cameraMardi +
                ", cameraNavcam=" + cameraNavcam +
                ", cameraPancam=" + cameraPancam +
                ", cameraMinites=" + cameraMinites +
                ", rememberPreferences=" + rememberPreferences +
                '}';
    }
}
