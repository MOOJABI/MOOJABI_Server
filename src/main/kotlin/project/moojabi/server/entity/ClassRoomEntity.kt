package project.moojabi.server.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "tbl_class_room",
    uniqueConstraints = [
        UniqueConstraint(name = "class_room_name_uk", columnNames = arrayOf("name"))
    ]
)
class ClassRoomEntity(

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    val name: String

) : BaseEntity()
