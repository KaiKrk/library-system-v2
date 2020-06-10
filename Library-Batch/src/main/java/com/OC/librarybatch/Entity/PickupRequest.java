package com.OC.librarybatch.Entity;

public class PickupRequest {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PickupRequest(int id) {
        this.id = id;
    }

    public PickupRequest() {
    }

    @Override
    public String toString() {
        return "PickupRequest{" +
                "id=" + id +
                '}';
    }
}
