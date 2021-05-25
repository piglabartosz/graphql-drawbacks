package com.example.newhorizonsgraphql

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLType
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Component
class CustomScalarsConfiguration : SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        ZonedDateTime::class -> ExtendedScalars.DateTime
        else -> null
    }
}
