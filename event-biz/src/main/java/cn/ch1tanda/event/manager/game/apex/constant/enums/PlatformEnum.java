package cn.ch1tanda.event.manager.game.apex.constant.enums;

/**
 * 平台枚举
 */
public enum PlatformEnum {
    PC("PC", "Origin or Steam"),
    PS4("PS4", "Playstation 4/5"),
    X1("X1", "Xbox"),
    SWITCH("SWITCH", "Nintendo Switch"),
    ;

    private final String code;
    private final String value;

    private PlatformEnum (String code, String value) {
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
