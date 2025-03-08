package com.harshsinghio.passportseva.presentation.common.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Utility class for date and time operations
 */
object DateTimeUtil {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private val fullDateFormatter = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
    private val dayFormatter = SimpleDateFormat("EEE", Locale.getDefault())
    private val dayNumberFormatter = SimpleDateFormat("d", Locale.getDefault())
    private val monthYearFormatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    /**
     * Format a date string to a full date string (e.g., "Monday, January 1, 2025")
     *
     * @param dateString Date string in format "yyyy-MM-dd"
     * @return Formatted full date string
     */
    fun formatToFullDate(dateString: String): String {
        return try {
            val date = dateFormatter.parse(dateString)
            date?.let { fullDateFormatter.format(it) } ?: dateString
        } catch (e: Exception) {
            dateString
        }
    }

    /**
     * Format a time string to a readable time (e.g., "10:30 AM")
     *
     * @param timeString Time string in format "HH:mm"
     * @return Formatted time string
     */
    fun formatTime(timeString: String): String {
        return try {
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = timeFormat.parse(timeString)
            date?.let { timeFormatter.format(it) } ?: timeString
        } catch (e: Exception) {
            timeString
        }
    }

    /**
     * Get the day of week abbreviation from a date string
     *
     * @param dateString Date string in format "yyyy-MM-dd"
     * @return Day abbreviation (e.g., "Mon")
     */
    fun getDayOfWeek(dateString: String): String {
        return try {
            val date = dateFormatter.parse(dateString)
            date?.let { dayFormatter.format(it) } ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Get the day number from a date string
     *
     * @param dateString Date string in format "yyyy-MM-dd"
     * @return Day number (e.g., "15")
     */
    fun getDayNumber(dateString: String): String {
        return try {
            val date = dateFormatter.parse(dateString)
            date?.let { dayNumberFormatter.format(it) } ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Get the month and year from a date string
     *
     * @param dateString Date string in format "yyyy-MM-dd"
     * @return Month and year (e.g., "January 2025")
     */
    fun getMonthYear(dateString: String): String {
        return try {
            val date = dateFormatter.parse(dateString)
            date?.let { monthYearFormatter.format(it) } ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Generate a list of dates for a calendar view
     *
     * @param startDate Start date string in format "yyyy-MM-dd"
     * @param days Number of days to generate
     * @return List of date strings
     */
    fun generateDateList(startDate: String, days: Int): List<String> {
        val result = mutableListOf<String>()
        try {
            val date = dateFormatter.parse(startDate) ?: return result
            val calendar = Calendar.getInstance()
            calendar.time = date

            for (i in 0 until days) {
                result.add(dateFormatter.format(calendar.time))
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
        } catch (e: Exception) {
            // Return empty list on error
        }
        return result
    }

    /**
     * Check if a date is today
     *
     * @param dateString Date string in format "yyyy-MM-dd"
     * @return True if the date is today
     */
    fun isToday(dateString: String): Boolean {
        return try {
            val date = dateFormatter.parse(dateString) ?: return false
            val today = Date()
            val todayString = dateFormatter.format(today)
            val dateToCheck = dateFormatter.format(date)
            todayString == dateToCheck
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Get the current date string in format "yyyy-MM-dd"
     *
     * @return Current date string
     */
    fun getCurrentDate(): String {
        return dateFormatter.format(Date())
    }

    /**
     * Get the current time string in format "hh:mm a"
     *
     * @return Current time string
     */
    fun getCurrentTime(): String {
        return timeFormatter.format(Date())
    }
}