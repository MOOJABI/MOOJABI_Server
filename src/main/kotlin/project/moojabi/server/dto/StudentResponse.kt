package project.moojabi.server.dto

data class StudentResponse(
    val students: List<StudentInfo>
) {
    data class StudentInfo(
        val id: Long,
        val gcn: String,
        val name: String,
    )
}
