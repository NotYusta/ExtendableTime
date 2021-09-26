package id.raznar.extendedtime;

import id.raznar.extendedtime.Util.Lang;
import id.raznar.extendedtime.Util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;

public class Commands implements CommandExecutor, TabExecutor {
    private final CMDResponses responses;

    public Commands(ExtendableTime main) {
        this.responses = new CMDResponses();

        main.getCommand("extendabletime").setTabCompleter(this);
        main.getCommand("extendabletime").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        if(command.getName().equalsIgnoreCase("extendabletime")) {
            if(sender.hasPermission("extendabletime.admin")) {
                if (args.length == 0 || arguments.isEmpty()) {
                    responses.sendHelpMessage(sender);
                } else {
                    switch(args[0].toLowerCase()) {
                        case "help":
                            responses.sendHelpMessage(sender);
                            break;
                        case "reload":
                            responses.reloadConfig(sender);
                            break;
                    }
                }
            } else {
                Utils.sendMessage(sender, Lang.ERROR_COMMAND_PERMISSION_REQUIRED);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("extendabletime")) {
            if(args.length == 1) {
                String[] cmdsArgs = {"help", "reload", "setday", "setnight"};

                ArrayList<String> commandList = new ArrayList<>(Arrays.asList(cmdsArgs));
                Collections.sort(commandList);

                return commandList;
            }
        }
        return null;
    }
}
