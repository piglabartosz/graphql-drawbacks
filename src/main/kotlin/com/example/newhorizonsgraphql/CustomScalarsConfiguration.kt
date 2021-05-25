package com.example.newhorizonsgraphql

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Component
class CustomScalarsConfiguration : SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        Instant::class -> graphqlInstantType
        else -> null
    }
}

val graphqlInstantType: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Instant")
    .description("A type representing a formatted java.time.Instant")
    .coercing(InstantCoercing)
    .build()

/**
 * TODO handle time zone correctly!
 */
object InstantCoercing : Coercing<Instant, String> {
    override fun parseValue(input: Any?): Instant =
        LocalDateTime.parse(serialize(input))
            .atZone(ZoneId.of("America/Toronto"))
            .toInstant()

    override fun parseLiteral(input: Any?): Instant? {
        val instantString = (input as? StringValue)?.value
        return Instant.parse(instantString)
    }

    override fun serialize(dataFetcherResult: Any?): String = dataFetcherResult.toString()
}