package com.example.gs_mobile

import java.sql.Connection
import java.sql.DriverManager

object OracleDatabaseConfig {

    private const val URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521/orcl"
    private const val USERNAME = "rm552247 "
    private const val PASSWORD = "111205"

    fun getConnection(): Connection {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD)
    }
}
