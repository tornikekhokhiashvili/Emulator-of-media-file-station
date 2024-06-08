package com.epam.static.delegation

import java.lang.IllegalArgumentException

/**
 * Represents a file which our [MediaFileProcessor] is operating with
 *
 * Requirements:
 * - file should have only `mkv` extension
 * - [name] should have the next structure: "VideoFileN.mkv",
 *   where "N" is a number of the file (from 0 to 9)
 * - [size] should be >= 0
 * Tips:
 * - in ensure correct behaviour in collections don't forget about
 *   equals() and hashCode()
 */
class File(val size: Int, val name: String) {

    companion object {
        const val DOT = "."
        private const val INVALID_FILE_NAME_MESSAGE = "File name should have `.mkv` extension"
        private const val INVALID_FILE_SIZE_MESSAGE = "File size should be non-negative"
    }

    init {
        require(name.endsWith(DOT+"mkv")) { INVALID_FILE_NAME_MESSAGE}
        require(size >= 0) { INVALID_FILE_SIZE_MESSAGE }
    }


    /**
     * Emulates editing process of the video files. Creating a new file
     * with extended [name] and added up [size]
     *
     * Requirements:
     * - result file size should be a sum of the first and second files sizes
     * - result file name should have next structure: "VideoFileN+R.mkv",
     *   where "N" is a number of the first file, "R" is a number of the second file
     * - when any of files has invalid name then [IllegalArgumentException]
     *   should be thrown
     * Tips:
     * - constant [DOT] could be useful to divide file name and it extension
     * - in order to simplify this function maybe initial checks for the file name
     *   and size will be suitable to put into `init` block
     *
     * @param file which will be added to the current one
     * @return new file with a total size and extended name
     */
    operator fun plus(file: File): File {
            val currentNumberSubstring = name.substringAfter("File").substringBefore(DOT)
            val nextNumberSubstring = file.name.substringAfter("File").substringBefore(DOT)
            val currentLastChar = currentNumberSubstring.last()
            val nextLastChar = nextNumberSubstring.last()
            if (!currentLastChar.isDigit() || !nextLastChar.isDigit()) {
                throw IllegalArgumentException()
            }
            val newSize = size + file.size
            val currentFileName = name.substringBefore(currentNumberSubstring)
            val newFileName = "$currentFileName${currentNumberSubstring}+${nextNumberSubstring}${DOT}mkv"
            return File(newSize, newFileName)
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is File) return false
        if (size != other.size) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        var result = size
        result = 31 * result + name.hashCode()
        return result
    }
    override fun toString(): String {
        return "File(name=$name, size=$size)"
    }

}