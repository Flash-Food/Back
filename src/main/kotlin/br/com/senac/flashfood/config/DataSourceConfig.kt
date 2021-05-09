package br.com.senac.flashfood.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * Responsible class per to provide the DataSource the to Spring
 */

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
class DataSourceConfig {

    val poolName                        = "HikariCP"

    lateinit var driverClassName        : String

    lateinit var username               : String

    lateinit var password               : String

    lateinit var jdbcUrl                : String

    lateinit var connectionTimeout      : String

    lateinit var idleTimeout            : String

    lateinit var maxLifetime            : String

    lateinit var minimumIdle            : String

    lateinit var maximumPoolSize        : String

    @Bean
    fun datasource() : DataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.driverClassName = driverClassName
        hikariConfig.jdbcUrl = jdbcUrl
        hikariConfig.username = username
        hikariConfig.password = password
        hikariConfig.minimumIdle = minimumIdle.toInt()
        hikariConfig.maximumPoolSize = maximumPoolSize.toInt()
        hikariConfig.connectionTimeout = connectionTimeout.toLong()
        hikariConfig.idleTimeout = idleTimeout.toLong()
        hikariConfig.maxLifetime = maxLifetime.toLong()
        hikariConfig.poolName = poolName

        return HikariDataSource(hikariConfig)
    }
}