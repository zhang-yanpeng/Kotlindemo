package com.zyp.kotlin.views;

public enum PasswordMode {
    /**
     * 下划线样式
     */
    UNDERLINE(0),

    /**
     * 边框样式
     */
    RECT(1);

    /**
     * 密码框模式
     */
    private int mode;

    PasswordMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return this.mode;
    }

    public static PasswordMode formMode(int mode) {
        for (PasswordMode m : values()) {
            if (mode == m.mode) {
                return m;
            }
        }
        throw new IllegalArgumentException();
    }
}