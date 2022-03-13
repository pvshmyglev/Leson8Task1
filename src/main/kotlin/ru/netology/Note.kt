package ru.netology

data class Note(
    val id: Int, //Идентификатор заметки.
    val ownerId: Int, //Идентификатор владельца заметки.
    val title: String, //Заголовок заметки.
    val text: String, //Текст заметки.
    val date: Int, //Дата создания заметки в формате Unixtime.
    var comments: Int, //Количество комментариев.
    val readComments: Int, //Количество прочитанных комментариев (только при запросе информации о заметке текущего пользователя).
    val viewUrl: String, //URL страницы для отображения заметки.
    val privacyView: String, //Настройки приватности комментирования заметки
    val canComment: Boolean,  //Есть ли возможность оставлять комментарии
    val textWiki: String, //Тэги ссылок на wiki
    var deleted: Boolean //Поментка удаления
)
{
    var commentsList = mutableListOf<Comment>()

    constructor(id: Int, title: String, text: String) : this(
        id,
        0,
        title,
        text,
        0,
        (System.currentTimeMillis() / 1000L).toInt(),
        0,
        "",
        "",
        true,
        "",
    false) { this.commentsList = mutableListOf<Comment>()}

}
