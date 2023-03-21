package project.moojabi.server.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.moojabi.server.dto.ClassRoomResponse
import project.moojabi.server.dto.ClassRooms
import project.moojabi.server.entity.ClassRoomEntity
import project.moojabi.server.entity.ClassRoomRepository
import project.moojabi.server.error.Exceptions

@Service
class ClassRoomService(
    private val classRoomRepository: ClassRoomRepository
) {

    @Transactional(readOnly = true)
    fun searchClassRoom(name: String?): ClassRooms {
        val classRooms = name?.run {
            classRoomRepository.findAllByNameIsContaining(name)
        } ?: classRoomRepository.findAll()

        return ClassRooms(
            classRooms.map {
                ClassRoomResponse(
                    id = it.id,
                    name = it.name
                )
            }
        )
    }

    @Transactional
    fun createClassRoom(name: String): ClassRoomResponse {
        if (classRoomRepository.existsByName(name)) {
            throw Exceptions.AlreadyExists("이름이 '$name'인 학급이 이미 존재합니다.")
        }

        val savedClassRoom = classRoomRepository.save(ClassRoomEntity(name))

        return ClassRoomResponse(savedClassRoom.id, savedClassRoom.name)
    }
}
