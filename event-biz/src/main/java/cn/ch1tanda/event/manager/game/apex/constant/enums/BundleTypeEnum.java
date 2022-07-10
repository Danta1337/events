package cn.ch1tanda.event.manager.game.apex.constant.enums;

public enum BundleTypeEnum {
    PERMANENT("permanent", "永久存在"),
    DAILY("daily", "每日更新"),
    ;

    private final String code;
    private final String value;

    BundleTypeEnum(String code, String value) {
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
