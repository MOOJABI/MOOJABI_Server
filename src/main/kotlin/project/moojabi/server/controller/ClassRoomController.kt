package project.moojabi.server.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import project.moojabi.server.dto.ClassRoomRequest
import project.moojabi.server.dto.ClassRoomResponse
import project.moojabi.server.dto.ClassRooms
import project.moojabi.server.service.ClassRoomService

@RestController
@RequestMapping("/class-room")
class ClassRoomController(
    private val classRoomService: ClassRoomService
) {

    @GetMapping
    fun searchClassRoom(@RequestParam(required = false) name: String?): ClassRooms {
        return classRoomService.searchClassRoom(name)
    }

    @PostMapping
    fun createClassRoom(@RequestBody request: ClassRoomRequest): ClassRoomResponse {
        return classRoomService.createClassRoom(request.name)
    }
}
