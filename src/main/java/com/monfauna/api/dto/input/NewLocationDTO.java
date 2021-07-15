package com.monfauna.api.dto.input;

import com.monfauna.api.model.Location;

public class NewLocationDTO {

    private String name;
    private String latitude;
    private String longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Location toModel() {
        Location location = new Location();
        location.setName(name);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }
}
