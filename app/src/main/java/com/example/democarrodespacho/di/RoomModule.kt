package com.example.democarrodespacho.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.democarrodespacho.data.database.CarroCompraDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "carro_compra_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CarroCompraDataBase::class.java, QUOTE_DATABASE_NAME).addMigrations(MIGRATION_2_3).build()

    @Singleton
    @Provides
    fun provideCarroCompraDao(db: CarroCompraDataBase) = db.getCarroCompraDao()

    private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // The following query will add a new column called lastUpdate to the notes database
            //database.execSQL("ALTER TABLE cliente_pedidos CHANGE fecha fecha DATETIME NULL")
            database.execSQL("CREATE TABLE 'productos' ('id' INTEGER primary key not null, 'nombre' TEXT, 'precio' INTEGER, 'foto' TEXT ) ")
        }
    }

    private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE 'clientes' ('id' INTEGER primary key not null, 'nombre' TEXT, 'rut' TEXT, 'email' TEXT, 'direccion' TEXT ) ")
        }
    }

}