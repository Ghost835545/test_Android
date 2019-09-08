package com.example.ivan.loginapp.util;

import java.util.ArrayList;
import java.util.List;

public class Disciplines {
    public String string;
    public final List<String> children = new ArrayList<>();
    public Disciplines(String string) {
        this.string = string;
    }
}
