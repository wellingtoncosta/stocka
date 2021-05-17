package dev.wellingtoncosta.stocka.data.db

import com.squareup.sqldelight.db.SqlDriver

class StockaDatabaseFactory(private val driver: SqlDriver) {
    fun create(): StockaDatabase {
        return StockaDatabase(
            driver,
            defensiveAdapter = DefensiveAdapter(),
            fertilizerAdapter = FertilizerAdapter(),
            seedAdapter = SeedAdapter()
        )
    }
}