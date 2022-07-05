package xmmt.dituon.share

import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
data class RarityConfig(
    val probability: Float
) {
    companion object {
        @JvmStatic
        fun getRarityConfig(str: String): RarityConfig {
            return Json.decodeFromString(str)
        }
    }
}

@Serializable
data class ElementConfig(
    val icon: List<IconImage>
) {
    companion object {
        @JvmStatic
        fun getElementConfig(str: String): ElementConfig {
            return Json.decodeFromString(str)
        }
    }
}

@Serializable
data class IconImage constructor(
    val image: String,
//    val pos: List<Int>
)