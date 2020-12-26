package ru.fineservices.smlr.model

import javax.persistence.*
import javax.persistence.GenerationType.SEQUENCE

@Entity
@Table(name = "links")
data class Link(
        var text: String = "",

        @Id
        @GeneratedValue(strategy = SEQUENCE, generator = "links_sequence")
        @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq")
        var id: Long = 0
)
