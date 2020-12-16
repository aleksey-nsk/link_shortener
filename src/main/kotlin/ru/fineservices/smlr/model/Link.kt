package ru.fineservices.smlr.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "links")
data class Link(
        var text: String = "",
        @Id @GeneratedValue(strategy = AUTO) var id: Long = 0
)
