package id.raznar.extendedtime.Util;

import lombok.Getter;

public enum Lang {
    ERROR_COMMAND_DOESNT_EXIST("&cUnknown command"),
    ERROR_PLAYER_NOT_ENOUGH_ARGUMENT("&cNot enough argument! Correct usage: {0}"),
    ERROR_COMMAND_PERMISSION_REQUIRED("&cYou don't have the required Permission to run this command!"),
    ERROR_COMMAND_ARGUMENT("&cWrong argument! Correct usage: {0}");

    @Getter public String message;
    Lang(String message) {
        this.message = Utils.colorize(message);
    }
}
