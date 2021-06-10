package world.cepi.kepi.command

import world.cepi.kepi.command.subcommand.Help

internal object HelpCommand: Help("""
    Need help with a command? Run
    <yellow>/(command), /(command) help, <gray>or<yellow> /(command) ? for more info.
    
    If you need extra help, ask questions
    in our <blue>/discord!
""".trimIndent())