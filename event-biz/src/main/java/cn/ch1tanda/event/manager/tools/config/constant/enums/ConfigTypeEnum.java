package cn.ch1tanda.event.manager.tools.config.constant.enums;

public enum ConfigTypeEnum {
    DEFAULT("DEFAULT", "默认配置类型"),
    GAME("GAME", "游戏"),
    FILE("FILE", "文件系统")
    ;

    private final String code;
    private final String value;

    ConfigTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
