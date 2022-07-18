package cn.ch1tanda.event.manager.picture.pixiv.constant.enums;

public enum SizeEnum {

    ORIGINAL("original", "原始"),
    REGULAR("regular", "通常"),
    SMALL("small", "小"),
    THUMB("thumb", "更小"),
    MINI("mini", "迷你"),
    ;

    private final String code;
    private final String value;

    SizeEnum(String code, String value) {
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
