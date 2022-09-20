package com.alibaba.cloud.youxia.dto;
import lombok.Data;
@Data
public class SkywalkingWhiteList {

    private String serviceCode;
    private String endpointName;
    private String peer;
    private String component;
    private String url;
    private String errorKind;
    private String message;
    private String stack;

}
