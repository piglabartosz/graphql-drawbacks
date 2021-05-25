package com.example.newhorizonsgraphql

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class MentorInitializer(
    private val mentorRepository: MentorRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        mentorRepository.deleteAll().block()
        mentorRepository.saveAll(
            listOf(
                Mentor(
                    name = "John", age = 18,
                    sessions = listOf(
                        Session(time = Instant.now(), secretData = "secret")
                    )
                )
            )
        ).blockLast()
    }
}