package project.moojabi.server.entity

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassRoomRepository : CrudRepository<ClassRoomEntity, Long> {
    fun existsByName(name: String): Boolean
    fun findAllByNameIsContaining(name: String?): List<ClassRoomEntity>
}
