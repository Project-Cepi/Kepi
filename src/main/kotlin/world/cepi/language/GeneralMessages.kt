package world.cepi.language

import net.minestom.server.chat.JsonMessage

enum class GeneralMessages : LocalizableEnum<JsonMessage> {

    PLAYER_ONLY_COMMAND,

    SUB_COMMAND_NOT_FOUND,
    SUB_COMMAND_NOT_FOUND_INFO,
    SUB_COMMAND_NOT_FOUND_LIST,
    SUB_COMMAND_NOT_FOUND_INFO_LIST,

    NO_PERMISSIONS,
    NO_PERMISSIONS_INFO,

    PLAYER_NOT_ONLINE,
    PLAYER_NOT_ONLINE_INFO,
    PLAYER_NOT_FOUND,
    PLAYER_NOT_FOUND_INFO,

    USAGE_INFO,

    ERROR,
    ERROR_INFO,

    EXAMPLE_WELCOME,

    ;

    override lateinit var contained: Localizable<JsonMessage>

}