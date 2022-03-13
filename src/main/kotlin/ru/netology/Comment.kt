package ru.netology

data class Comment(
    val noteId: Int, //Идентификатор заметки.
    val ownerId: Int, //Идентификатор владельца заметки.
    val replyTo: Int?, //Идентификатор пользователя, ответом на комментарий которого является добавляемый комментарий (не передаётся, если комментарий не является ответом).
    val message: String, //Текст комментария.
    val id: Int, //Уникальный идентификатор, предназначенный для предотвращения повторной отправки одинакового комментария.
    var deleted: Boolean //Поментка удаления
){
    constructor(noteId: Int, message: String, id: Int) : this(
        noteId,
        0,
        0,
        message,
        id,
    false)


}
