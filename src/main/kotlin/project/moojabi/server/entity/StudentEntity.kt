package project.moojabi.server.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "tbl_student",
    uniqueConstraints = [
        UniqueConstraint(name = "student_gcn_uk", columnNames = arrayOf("class_room_id", "gcn"))
    ]
)
class StudentEntity(

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_room_id", columnDefinition = "BIGINT", nullable = false)
    val classRoom: ClassRoomEntity,

    @Column(name = "gcn", columnDefinition = "VARCHAR(5)", nullable = false)
    val gcn: String,

    @Column(name = "name", columnDefinition = "VARCHAR(5)", nullable = false)
    val name: String

) : BaseEntity()
