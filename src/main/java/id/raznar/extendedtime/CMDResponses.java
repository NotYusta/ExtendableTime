package id.raznar.extendedtime;

import id.raznar.extendedtime.Util.Utils;
import org.bukkit.command.CommandSender;

public class CMDResponses {

    public CMDResponses() {
    }

    public void sendHelpMessage(CommandSender sender) {
        Utils.sendMessage(sender,
                "&eExtended Time Commands:" +
                        "\n&m&b[-----------------------]" +
                        "\n/extime reload" +
                        "\n/extime help" +
                        "\n&m&b[-----------------------]" +
                        "\n&eMade with love by Yusta <3"
                );
    }

    public void reloadConfig(CommandSender sender) {
        Utils.sendMessage(sender, "&aSuccessfully reloaded Config");
    }
}
