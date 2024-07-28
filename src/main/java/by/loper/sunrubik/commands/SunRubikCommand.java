package by.loper.sunrubik.commands;

import by.loper.sunrubik.SunRubik;
import by.loper.sunrubik.utils.ItemManager;
import by.loper.sunrubik.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SunRubikCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender.hasPermission("sunRubik.*")) {
            if (args.length == 0) {
                commandSender.sendMessage("§6§lКУБИК РУБИКА §fПомощь по плагину");
                commandSender.sendMessage("§6");
                commandSender.sendMessage("§e/sunRubik give (игрок) (кол-во) §8| §fВыдает игроку кубик рубика");
                commandSender.sendMessage("§e/sunRubik reload §8| §fПерезагрузка плагина");
                commandSender.sendMessage("§6");
                return true;
            } else if (args[0].equalsIgnoreCase("give")) {
                if (args.length == 1) {
                    commandSender.sendMessage("§6§lКУБИК РУБИКА §fПомощь по плагину");
                    commandSender.sendMessage("§6");
                    commandSender.sendMessage("§e/sunRubik give (игрок) (кол-во) §8| §fВыдает игроку кубик рубика");
                    commandSender.sendMessage("§e/sunRubik reload §8| §fПерезагрузка плагина");
                    commandSender.sendMessage("§6");
                    return true;
                } else if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player getPlayer = Bukkit.getPlayer(args[1]);
                        getPlayer.getInventory().addItem(ItemManager.getRubik(1));
                        commandSender.sendMessage(Utils.cfgMessage("messages.giveRubikSender").replace("%player%", args[1]).replace("%amount%", "1"));
                        getPlayer.sendMessage(Utils.cfgMessage("messages.giveRubik").replace("%amount%", "1"));
                        return true;
                    }
                } else if (args.length == 3) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        if (StringUtils.isNumeric(args[2])) {

                            Player getPlayer = Bukkit.getPlayer(args[1]);
                            getPlayer.getInventory().addItem(ItemManager.getRubik(Integer.parseInt(args[2])));
                            commandSender.sendMessage(Utils.cfgMessage("messages.giveRubikSender").replace("%player%", args[1]).replace("%amount%", args[2]));
                            getPlayer.sendMessage(Utils.cfgMessage("messages.giveRubik").replace("%amount%", args[2]));
                            return true;

                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                SunRubik.getInstance().reloadConfig();
                commandSender.sendMessage(Utils.cfgMessage("messages.reloadPlugin"));
            } else {
                commandSender.sendMessage("§6§lКУБИК РУБИКА §fПомощь по плагину");
                commandSender.sendMessage("§6");
                commandSender.sendMessage("§e/sunRubik give (игрок) (кол-во) §8| §fВыдать игроку кубик рубика");
                commandSender.sendMessage("§e/sunRubik reload §8| §fПерезагрузка плагина");
                commandSender.sendMessage("§6");

                return true;
            }
        }else{
            commandSender.sendMessage(Utils.cfgMessage("messages.noPerms"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> completer = new ArrayList<>();
        if(commandSender.hasPermission("sunRubik.*")) {
            if (args.length == 1) {
                completer.add("give");
                completer.add("reload");
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("give")) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        completer.add(p.getName());
                    }
                }
            }
        }
        return completer;
    }
}
