package ltd.guimc.lgzbot.command

import kotlinx.coroutines.launch
import ltd.guimc.lgzbot.PluginMain
import net.mamoe.mirai.console.command.BuiltInCommands.HelpCommand.generateDefaultHelp
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.ConsoleCommandSender.permitteeId
import net.mamoe.mirai.console.command.ConsoleCommandSender.sendMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.command.isConsole

object HelpCommand: SimpleCommand (
    owner = PluginMain,
    primaryName = "h",
    description = "."
) {
    @Handler
    fun CommandSender.onHandler() = ltd_guimc_command_help()

    fun CommandSender.ltd_guimc_command_help() = launch{
        if (isConsole()) {
            sendMessage(generateDefaultHelp(permitteeId))
        } else {
            require(bot != null)
            sendMessage("请稍等")
            sendMessage(PluginMain.helpMessage!!)
        }
    }
}