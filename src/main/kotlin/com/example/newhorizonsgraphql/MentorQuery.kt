package com.example.newhorizonsgraphql

import com.expediagroup.graphql.generator.execution.OptionalInput
import com.expediagroup.graphql.server.operations.Query
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class MentorQuery(
    private val mentorRepository: MentorRepository
) : Query {

    /**
     * 1. Do you want to use Mono or Flux?
     * Write boilerplate code: https://expediagroup.github.io/graphql-kotlin/docs/schema-generator/execution/async-models/
     * 2. You can't use Sort, Pageable
     * 3. Optional arguments? This library does not have any annotation for optional argument
     * so you can't write in this way: sortFieldName: String = "name". You can write only in this way: sortFieldName: OptionalInput<String>,
     * Details here: https://expediagroup.github.io/graphql-kotlin/docs/schema-generator/writing-schemas/arguments/#default-values
     */
    fun mentors(
        maxAge: Int,
        sortFieldNameOptional: OptionalInput<String>,
        sortFieldDirectionOptional: OptionalInput<Direction>
    ): CompletableFuture<List<Mentor>> {
        var sort = Sort.by(Direction.ASC, "name")

        val sortFieldName = when (sortFieldNameOptional) {
            is OptionalInput.Undefined -> null
            is OptionalInput.Defined<String> -> sortFieldNameOptional.value
        }

        val sortFieldDirection = when (sortFieldDirectionOptional) {
            is OptionalInput.Undefined -> null
            is OptionalInput.Defined<Direction> -> sortFieldDirectionOptional.value
        }

        if (sortFieldName != null && sortFieldDirection != null) {
            sort = Sort.by(sortFieldDirection, sortFieldName)
        }

        return mentorRepository.findAll(sort).filter { it.age <= maxAge }.collectList().toFuture()
    }
}