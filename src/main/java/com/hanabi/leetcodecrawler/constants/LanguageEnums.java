package com.hanabi.leetcodecrawler.constants;

/**
 * 语言类型枚举
 */
public enum LanguageEnums {
    CPP(0, "cpp"),
    JAVA(1, "java"),
    PYTHON(2, "python"),
    PYTHON3(3, "python3"),
    C(4, "c"),
    CSHARP(5, "csharp"),
    JAVASCRIPT(6, "javascript"),
    RUBY(7, "ruby"),
    SWIFT(8, "swift"),
    GOLANG(9, "golang"),
    SCALA(10, "scala"),
    KOTLIN(11, "kotlin"),
    RUST(12, "rust"),
    PHP(13, "php"),
    TYPESCRIPT(14, "typescript");


    private String name;
    private int code;

    LanguageEnums(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据指定枚举的code查找对应名称，若未找到，返回空字符串
     */
    public static String getName(int code) {
        for (LanguageEnums language : LanguageEnums.values()) {
            if (language.getCode() == code) {
                return language.getName();
            }
        }
        return "";
    }

    /**
     * 根据指定枚举的名称查找对应code，若未找到，返回-1
     */
    public static int getCode(String name) {
        for (LanguageEnums language : LanguageEnums.values()) {
            if (language.getName().equals(name)) {
                return language.getCode();
            }
        }
        return -1;
    }
}
