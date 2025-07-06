package meshkit.config.catalog

import com.sksamuel.hoplite.*
import com.sksamuel.hoplite.decoder.Decoder
import com.sksamuel.hoplite.decoder.toValidated
import com.sksamuel.hoplite.fp.invalid
import java.util.Locale
import kotlin.reflect.KType

data class EntityReference(val kind: EntityKind, val namespace: String?, val name: String) {

    companion object {
        fun parse(str: String): EntityReference {
            val parts = str.split(":")
            if (parts.size != 2) {
                throw IllegalArgumentException("Invalid EntityReference format: $str")
            }
            val kind = parseKind(parts[0])
            return parts[1].split("/").let {
                when (it.size) {
                    1 -> EntityReference(kind, null, it[0])
                    2 -> EntityReference(kind, it[0], it[1])
                    else -> throw IllegalArgumentException("Invalid EntityReference format: $str")
                }
            }
        }

        private fun parseKind(kindString: String): EntityKind {
            val map = EntityKind.entries.associateBy { it.name.lowercase(Locale.getDefault()) }
            return map[kindString.lowercase(Locale.getDefault())] ?: throw IllegalArgumentException("Unknown entity kind: $kindString")
        }
    }

    override fun toString(): String {
        val prefix = if (namespace != null && namespace.isNotBlank()) {
            "$namespace/"
        } else {
            ""
        }
        return "$kind:$prefix$name"
    }

    val id
        get() = if (namespace != null && name.isNotBlank()) {
            "$namespace/$name"
        } else {
            name
        }

    object EntityReferenceDecoder : Decoder<EntityReference> {
        override fun supports(type: KType): Boolean {
            return type.classifier == EntityReference::class
        }

        override fun decode(
            node: Node,
            type: KType,
            context: DecoderContext
        ): ConfigResult<EntityReference> {
            return when (node) {
                is StringNode -> runCatching { parse(node.value) }.toValidated {
                    ConfigFailure.DecodeError(node, type)
                }

                else -> ConfigFailure.DecodeError(node, type).invalid()
            }
        }
    }
}