package com.untistore.utils.managers;

public class LockdownManager {
    private static boolean isServerInLockdown;


    public static void toggleLockdown() {
        isServerInLockdown = !isServerInLockdown;
    }

    public static boolean isServerInLockdown() {
        return isServerInLockdown;
    }


}
