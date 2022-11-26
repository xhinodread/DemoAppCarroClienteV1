package com.example.democarrodespacho.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.democarrodespacho.data.database.dao.ProductoDao
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.database.entities.ProductoEntity

@Database(
    version = 2,
    entities = [ProductoEntity::class, CarroCompraEntity::class],
)
//    entities = [CarroCompra::class, ProductoEntity::class],
//exportSchema = true,

//@TypeConverters(CarroCompraConverters::class)
abstract class CarroCompraDataBase: RoomDatabase() {

    abstract fun getCarroCompraDao(): ProductoDao


    /****
    abstract fun carroCompraDao(): CarroCompraDao
    abstract fun productosDao(): ProductosDao

    companion object{
        @Volatile
        private var INSTANCE: CarroCompraDataBase? = null

/****/
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
        // The following query will add a new column called lastUpdate to the notes database
            //database.execSQL("ALTER TABLE cliente_pedidos CHANGE fecha fecha DATETIME NULL")
            database.execSQL("CREATE TABLE 'productos' ('id' INTEGER primary key not null, 'nombre' TEXT, 'precio' INTEGER, 'foto' TEXT ) ")
            }
        }
/****/

        fun getDatabase(context: Context): CarroCompraDataBase{
            Log.d("getDatabase", context.toString())
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CarroCompraDataBase? {
            return Room.databaseBuilder(
                    context.applicationContext,
                    CarroCompraDataBase::class.java,
                "carro_compra_database"
                )
                .addMigrations(MIGRATION_1_2)
                .build()
        }


    }
****/
}