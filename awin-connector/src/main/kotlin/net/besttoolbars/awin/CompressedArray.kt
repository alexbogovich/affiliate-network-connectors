package net.besttoolbars.awin

class CompressedArray<T>(val data: Iterable<T>) {
    override fun toString(): String = data.joinToString(",")

    companion object {
        fun <T> of(vararg data: T): CompressedArray<T> = CompressedArray(data.asIterable())
    }
}

fun <T> Iterable<T>.asCompressedArray(): CompressedArray<T> = CompressedArray(this)