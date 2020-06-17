package org.warren.sca.rsc.gateway.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactVO implements Serializable {

    private int id;

    private String name;

    private String phone;

    private String addressString;

}
