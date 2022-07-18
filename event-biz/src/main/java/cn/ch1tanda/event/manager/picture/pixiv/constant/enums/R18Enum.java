package cn.ch1tanda.event.manager.picture.pixiv.constant.enums;

public enum R18Enum {

    IS_NOT_R18(0, "非R18"),
    IS_R18(1, "R18"),
    MIX(2, "混合"),
    ;

    private final Integer code;
    private final String value;

    R18Enum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
