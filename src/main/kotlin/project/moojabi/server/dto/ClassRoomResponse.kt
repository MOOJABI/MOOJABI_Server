package project.moojabi.server.dto

data class ClassRoomResponse(
    val id: Long,
    val name: String
)

data class ClassRooms(
    val classRooms: List<ClassRoomResponse>
)
