package convertidordivisas.data

object Constants {
    object Speech {
        const val HELP = """Hola! te ayudo con mucho gusto <break time="1s"/> me puede decir convierte 10 pesos en euros <break time="1s"/> si lo necesitas soporto varias divisas, Inténtalo, ahora dime ¿Cómo te puedo ayudar?"""

        const val WELCOME = "Bienvenido! Me puedes decir convert divisas o dime cancela para terminar, ¿Cómo te puedo ayudar?"

        const val STOP_BYE = """Adios y <say-as interpret-as="interjection">buena suerte</say-as>."""

        const val CONVERT = """<break time="1s"/> Puedes decirme Convertir divisa o cancela para salir, ¿Cómo te puedo ayudar?"""
    }

    object Cards {
        const val HELP_TEXT = "Ejemplo: Convierte 10 pesos en euros"
        const val HELP_TITLE = "Convertidor Divisas"

        const val CONVERT_TITLE = "Convertir Divisa"

        const val LAUNCH_TITLE = "¿Cómo te puedo ayudar?"
        const val LAUNCH_TEXT = "Dime convert divisas o cancela"

        const val STOP_TITLE = "Convertidor de Divisas"
        const val STOP_TEXT = "!Adios!"
    }
}