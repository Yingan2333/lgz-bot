package ltd.guimc.lgzbot.plugin


import ltd.guimc.lgzbot.plugin.command.*
import ltd.guimc.lgzbot.plugin.files.Config
import ltd.guimc.lgzbot.plugin.files.Data
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.permission.Permission
import net.mamoe.mirai.console.permission.PermissionId
import net.mamoe.mirai.console.permission.PermissionService
import net.mamoe.mirai.console.permission.PermissionService.Companion.hasPermission
import net.mamoe.mirai.console.permission.PermitteeId.Companion.permitteeId
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.name
import net.mamoe.mirai.console.plugin.version
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import kotlin.math.round

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        "ltd.guimc.lgzbot.plugin",
        "0.1.2",
        "LgzBot",
    ){
        author("Guimc")
    }
) {
    lateinit var notMuteMessagePush: Permission
    lateinit var notTalkativeMessagePush: Permission


    override fun onEnable() {
        logger.info("$name v$version Loading")
        registerPerms()
        registerCommands()
        registerEvents()
        Config.reload()
        Data.reload()
        logger.info("$name v$version Loaded")
    }

    override fun onDisable() {
        Config.save()
        Data.save()
    }

    private fun registerPerms() = PermissionService.INSTANCE.run {
        notMuteMessagePush = register(PermissionId("lgzbot.event.notpush", "mute"), "不推送禁言权限 (仅适用于群聊)")
        notTalkativeMessagePush = register(PermissionId("lgzbot.event.notpush", "talkative"), "不推送新龙王权限 (仅适用于群聊)")
    }

    private fun registerCommands() = CommandManager.run {
        registerCommand(LGZBotCommand)
        registerCommand(MusicCommand)
    }

    private fun registerEvents() = GlobalEventChannel.run {
        subscribeAlways<GroupMessageEvent> { event -> MessageFilter.filter(event) }
        subscribeAlways<MemberMuteEvent> {
            if (!it.group.permitteeId.hasPermission(notMuteMessagePush)) {
                it.group.sendMessage(
                    PlainText("[禁言推送] ") +
                    At(it.operatorOrBot) +
                    PlainText(" 禁言了 ") +
                    At(it.member) +
                    PlainText(" ,时长: ${round(it.durationSeconds / 60.0)} 分钟.")
                )
            }
        }
        subscribeAlways<MemberUnmuteEvent> {
            if (!it.group.permitteeId.hasPermission(notMuteMessagePush)) {
                it.group.sendMessage(
                    PlainText("[禁言推送] ") +
                    At(it.operatorOrBot) +
                    PlainText(" 解禁了 ") +
                    At(it.member)
                )
            }
        }
        subscribeAlways<GroupTalkativeChangeEvent> {
            if (!it.group.permitteeId.hasPermission(notTalkativeMessagePush)) {
                it.group.sendMessage(
                    PlainText("!!! ") +
                    At(it.now) +
                    PlainText(" 成为了新的龙王 !!!")
                )
            }
        }
        subscribeAlways<BotInvitedJoinGroupRequestEvent> { it.accept() }
        subscribeAlways<NewFriendRequestEvent> { it.accept() }
    }
}