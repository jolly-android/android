package com.phoenix.pillreminder.feature_alarms.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import androidx.work.WorkManager
import com.phoenix.pillreminder.feature_alarms.data.data_source.MedicineDatabase
import com.phoenix.pillreminder.feature_alarms.data.repository.MedicineRepositoryImpl
import com.phoenix.pillreminder.feature_alarms.domain.repository.AlarmScheduler
import com.phoenix.pillreminder.feature_alarms.domain.repository.MedicineRepository
import com.phoenix.pillreminder.feature_alarms.presentation.AndroidAlarmScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMedicineDatabase(app: Application): MedicineDatabase {
        return Room.databaseBuilder(
            app,
            MedicineDatabase::class.java,
            "medicine_data_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideMedicineRepository(db: MedicineDatabase): MedicineRepository{
        return MedicineRepositoryImpl(db.medicineDao())
    }

    @Provides
    @Singleton
    fun provideAlarmScheduler(
        repository: MedicineRepository,
        @ApplicationContext context: Context
    ): AlarmScheduler = AndroidAlarmScheduler(repository, context)

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Provides
    @Singleton
    fun providePreferencesDataStore(app: Application): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            app.preferencesDataStoreFile("pill_reminder_preferences")
        }

}