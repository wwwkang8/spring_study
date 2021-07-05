package com.kosta.chap02;

public class ErpClientFactory {

    private String ip;
    private String model;

    public ErpClientFactory(String ip, String model) {
        this.ip = ip;
        this.model = model;
    }

    public String getIp() {
        return ip;
    }

    public String getModel() {
        return model;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
