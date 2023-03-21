package project.moojabi.server.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.moojabi.server.dto.StudentResponse
import project.moojabi.server.entity.ClassRoomRepository
import project.moojabi.server.entity.StudentEntity
import project.moojabi.server.entity.StudentRepository
import project.moojabi.server.error.Exceptions

@Service
class StudentService(
    private val classRoomRepository: ClassRoomRepository,
    private val studentRepository: StudentRepository
) {

    @Transactional(readOnly = true)
    fun getAllStudents(classRoomId: Long): StudentResponse {
        val students = studentRepository.findAllByClassRoomId(classRoomId)

        return StudentResponse(
            students.map {
                StudentResponse.StudentInfo(
                    id = it.id,
                    gcn = it.gcn,
                    name = it.name
                )
            }
        )
    }

    @Transactional
    fun createStudentsBulk(classRoomId: Long, students: List<Pair<String, String>>): StudentResponse {
        val classRoom = classRoomRepository.findByIdOrNull(classRoomId)
            ?: throw Exceptions.NotFound("classRoomId가 '$classRoomId'에 해당하는 학급이 존재하지 않습니다.")

        val studentsToSave = students.map {
            if (studentRepository.existsByGcnAndClassRoom(it.first, classRoom)) {
                throw Exceptions.AlreadyExists("${classRoom.name} 학급에 학번이 '${it.first}'인 학생이 이미 존재합니다.")
            }

            StudentEntity(
                classRoom = classRoom,
                gcn = it.first,
                name = it.second
            )
        }

        val savedStudents = studentRepository.saveAll(studentsToSave)

        return StudentResponse(
            savedStudents.map {
                StudentResponse.StudentInfo(
                    id = it.id,
                    gcn = it.gcn,
                    name = it.name
                )
            }
        )
    }

    @Transactional
    fun deleteStudent(studentId: Long) {
        studentRepository.deleteById(studentId)
    }
}
