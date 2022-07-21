package ltd.guimc.lgzbot.plugin.command

import kotlinx.coroutines.delay
import ltd.guimc.lgzbot.plugin.PluginMain
import ltd.guimc.lgzbot.plugin.utils.RandomUtils
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import java.util.*

object LGZBotCommand: CompositeCommand (
    owner = PluginMain,
    primaryName = "lgzbot",
    description = "LGZBot插件的主命令"
) {
    @SubCommand("ping")
    @Description("看看机器人是否在线吧")
    suspend fun CommandSender.ping() {
        sendMessage("Pong!")
    }

    @SubCommand("atspam")
    @Description("F**k You!")
    suspend fun CommandSender.atspam(target: Member, times: Int, sleepTime: Int) {
        sendMessage("Ok! Processing...")
        repeat(times) {
            delay(sleepTime * 1000L)
            sendMessage(At(target) + PlainText(RandomUtils.randomText(6)))
        }
    }
}