package project.moojabi.server.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.moojabi.server.dto.StudentRequest
import project.moojabi.server.dto.StudentResponse
import project.moojabi.server.service.StudentService

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping("/{classRoomId}")
    fun getAllStudents(@PathVariable("classRoomId") classRoomId: Long): StudentResponse {
        return studentService.getAllStudents(classRoomId)
    }

    @PostMapping("/{classRoomId}")
    fun createStudentsBulk(
        @PathVariable("classRoomId") classRoomId: Long,
        @RequestBody request: StudentRequest
    ): StudentResponse {
        return studentService.createStudentsBulk(classRoomId, request.students)
    }

    @DeleteMapping("/{studentId}")
    fun deleteStudent(@PathVariable("studentId") studentId: Long) {
        studentService.deleteStudent(studentId)
    }
}
