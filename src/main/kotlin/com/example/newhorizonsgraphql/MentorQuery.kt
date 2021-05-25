package com.example.newhorizonsgraphql

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class MentorQuery(
    private val mentorRepository: MentorRepository
) : Query {

    /**
     * Do you want to use Mono or Flux?
     * Write boilerplate code: https://expediagroup.github.io/graphql-kotlin/docs/schema-generator/execution/async-models/
     */
    fun mentors(maxAge: Int): CompletableFuture<List<Mentor>> =
        mentorRepository.findAll().filter { it.age <= maxAge }.collectList().toFuture()
}