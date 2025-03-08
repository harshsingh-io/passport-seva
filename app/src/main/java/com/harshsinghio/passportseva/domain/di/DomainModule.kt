package com.harshsinghio.passportseva.domain.di

import com.harshsinghio.passportseva.domain.repository.AppointmentRepository
import com.harshsinghio.passportseva.domain.repository.AuthRepository
import com.harshsinghio.passportseva.domain.repository.ServicesRepository
import com.harshsinghio.passportseva.domain.repository.StatusRepository
import com.harshsinghio.passportseva.domain.usecase.appointment.BookAppointmentUseCase
import com.harshsinghio.passportseva.domain.usecase.appointment.GetAvailableLocationsUseCase
import com.harshsinghio.passportseva.domain.usecase.appointment.GetAvailableTimeSlotsUseCase
import com.harshsinghio.passportseva.domain.usecase.auth.LoginUseCase
import com.harshsinghio.passportseva.domain.usecase.auth.LogoutUseCase
import com.harshsinghio.passportseva.domain.usecase.auth.RegisterUseCase
import com.harshsinghio.passportseva.domain.usecase.services.GetQuickActionsUseCase
import com.harshsinghio.passportseva.domain.usecase.services.GetServicesUseCase
import com.harshsinghio.passportseva.domain.usecase.services.GetUpdatesUseCase
import com.harshsinghio.passportseva.domain.usecase.status.GetApplicationStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    // Appointment use cases
    @Provides
    @Singleton
    fun provideGetAvailableLocationsUseCase(repository: AppointmentRepository): GetAvailableLocationsUseCase {
        return GetAvailableLocationsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAvailableTimeSlotsUseCase(repository: AppointmentRepository): GetAvailableTimeSlotsUseCase {
        return GetAvailableTimeSlotsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideBookAppointmentUseCase(repository: AppointmentRepository): BookAppointmentUseCase {
        return BookAppointmentUseCase(repository)
    }

    // Auth use cases
    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    // Services use cases
    @Provides
    @Singleton
    fun provideGetQuickActionsUseCase(repository: ServicesRepository): GetQuickActionsUseCase {
        return GetQuickActionsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetServicesUseCase(repository: ServicesRepository): GetServicesUseCase {
        return GetServicesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUpdatesUseCase(repository: ServicesRepository): GetUpdatesUseCase {
        return GetUpdatesUseCase(repository)
    }

    // Status use cases
    @Provides
    @Singleton
    fun provideGetApplicationStatusUseCase(repository: StatusRepository): GetApplicationStatusUseCase {
        return GetApplicationStatusUseCase(repository)
    }
}