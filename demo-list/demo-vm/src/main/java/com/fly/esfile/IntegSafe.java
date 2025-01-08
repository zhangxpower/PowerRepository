package com.fly.esfile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntegSafe {
    private String original_id;
    private Integer serverId;
    private String name;
    private String version;
    private String path;
    private String remark;
    private long insertTime;
    private long updateTime;
    private String type;
    private String sourceArea;
    private String sourceTable;
    private String hostName;
    private String serverIp;
    private String serverLocalIp;
    private String osType;
    private String app_sys_code;
    private String phy_system_cname;
    private String phy_system_ename;
    private String oper_unit;
    private String dev_manage_a;
    private String app_manage_a;
    private String app_manage_b;
}
