package com.example.newhorizonsgraphql

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MentorRepository : ReactiveCrudRepository<Mentor, String>