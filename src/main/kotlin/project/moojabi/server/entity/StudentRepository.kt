package project.moojabi.server.entity

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CrudRepository<StudentEntity, Long> {
    fun existsByGcnAndClassRoom(gcn: String, classRoom: ClassRoomEntity): Boolean
    fun findAllByClassRoomId(classRoomId: Long): List<StudentEntity>
}
