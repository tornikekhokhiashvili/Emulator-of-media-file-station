package com.epam.static.delegation

class FileEditor(private val storage: FileStorage) : Editor {

    /**
     * Creates a new one long video by putting all the videos
     * from the [storage] together.
     *
     * Requirements:
     * - result output should have a structure: "Edited file is $file"
     *   where `file` is a result of this function
     * - if storage is empty ,then [IllegalStateException] should be thrown
     *   with a message: "Your storage is empty"
     */
    override fun edit() {
        check(storage.files.isNotEmpty()) { "Your storage is empty" }
        val editedFileName = "EditedVideo.mkv"
        val editedFileSize = storage.files.sumBy { it.size }
        val editedFile = File(editedFileSize, editedFileName)
        println("Edited file is ${editedFile.name}")
    }
}