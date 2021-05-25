package com.example.newhorizonsgraphql

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MentorRepository : ReactiveMongoRepository<Mentor, String>