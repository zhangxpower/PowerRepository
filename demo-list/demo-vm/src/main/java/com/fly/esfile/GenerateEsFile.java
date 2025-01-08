package com.fly.esfile;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GenerateEsFile {

    public static final List<String> componentNames = List.of("smtp", "memcached", "tomcat", "oracle", "Apache Shiro", "VMware", "Microsoft Exchange", "perl-Pod-Perldoc");
    public static final List<String> orgNames = List.of("机构-北京", "机构-杭州", "机构-广州", "机构-上海", "机构-天津", "机构-成都", "机构-重庆");
    public static final List<String> versions = List.of("1.1.0", "3.2.0", "4.3.0", "4.3.1", "4.3.2", "4.3.x", "4.3.9", "1.33.0", "1.2.11", "4.3.10");
    public static final List<String> hostNames = List.of("wnlsdx-x113-xafo", "wn-lsdxa4-232fo", "wnl-sdxa-fo", "wnl2-341dx-afo");
    public static final List<String> hosts = List.of("118.43.22.1", "112.43.22.1", "11.47.22.1", "117.43.29.1", "120.43.29.1", "55.43.29.1", "10.19.200.199");
    public static final List<String> contacts = List.of("张三 18038319911", "刘芷墨 liuzm@xx.zh", "陈手动 chensd@xx.zh");
    public static final List<String> os = List.of("oracle linux server 7.6_64bit",
            "redhat enterprise linux server6.7_64bit",
            "Windows Server 2019",
            "Ubuntu 20.04 LTS",
            "Debian 10 Buster",
            "Fedora 35",
            "SUSE Linux Enterprise Server 15",
            "FreeBSD 12");
    public static final List<AppVO> apps = List.of(new AppVO("W100012", "反XX检测系统", "N-AMDXL"),
            new AppVO("W400112", "自动化平台", "D-FEWFEW"),
            new AppVO("WA13112", "结算系统", "C-DWXXFW1L"),
            new AppVO("CA10412", "征信系统", "C-FEWCXXXW"),
            new AppVO("XA55012", "用户门户", "C-FWEFEW"),
            new AppVO("DA64012", "监控系统", "C-WEFWECFEW"),
            new AppVO("WW66012", "数据分析中心", "C-ESCDW"),
            new AppVO("WC77012", "加密统计数据", "C-DWXXL"));
    @Data
    @AllArgsConstructor
    public static class AppVO{
        private String code;
        private String cname;
        private String ename;
    }

    public static void write(int start, int end, String indexRepo, String filePath){
        FileUtil.del(filePath);
        File file = FileUtil.file(filePath);
        final List<String> content = new ArrayList<>(end - start);
        for(int i = start; i <= end; i++) {
            AppVO appVO = randObject(apps);
            IntegSafe safe = IntegSafe.builder().original_id(IdUtil.objectId())
                    .hostName(randValue(hostNames))
                    .serverId(i)
                    .version(randValue(versions))
                    .insertTime(System.currentTimeMillis())
                    .serverIp(randValue(hosts))
                    .app_sys_code(appVO.code)
                    .phy_system_cname(appVO.cname)
                    .phy_system_ename(appVO.ename)
                    .oper_unit(randValue(orgNames))
                    .app_manage_a(randValue(contacts))
                    .version(randValue(versions))
                    .name(randValue(componentNames))
                    .path("/usr/bin")
                    .sourceArea("private_cloud")
                    .sourceTable("安装包")
                    .osType(randValue(os))
                    .build();
            String jsonStr = JSONUtil.toJsonStr(safe);
            content.add("{ \"index\" : { \"_index\" : \""+ indexRepo +"\", \"_type\" : \"_doc\", \"_id\" : \""+ i +"\" } }");
            content.add(jsonStr+"\n");
        }
        FileUtil.appendLines(content, file, Charset.defaultCharset());
    }

    private static <T> T randObject(List<T> versions) {
        int index = RandomUtil.randomInt(versions.size() - 1);
        return versions.get(index);
    }

    private static String randValue(List<String> versions) {
        return randObject(versions);
    }
}
