package com.uplusion23.todoServer.Views;

public interface UserView {
    public static class Public {};
    public static class Admin extends Public {};
    public static class Internal extends Admin {};
}
