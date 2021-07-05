package com.kosta.chap02;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {

    private ErpClientFactory erpClientFactory;

    @Autowired(required = false)
    private Monitor monitor;

    @Autowired
    public void setErpClientFactory(ErpClientFactory erpClientFactory){
        this.erpClientFactory = erpClientFactory;
    }

}
