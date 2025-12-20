package com.example.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.FileProvider

object IntentHelper {
    
    // Communication Intents
    fun openDialer(context: Context, phoneNumber: String = "1234567890") {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Dialer not available")
        }
    }
    
    fun sendSMS(context: Context, phoneNumber: String = "1234567890", message: String = "Hello from Intent Demo!") {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phoneNumber")
                putExtra("sms_body", message)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "SMS app not available")
        }
    }
    
    fun sendEmail(context: Context, email: String = "example@example.com", subject: String = "Demo Email", body: String = "This is a demo email from Intent Demo app!") {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Email app not available")
        }
    }
    
    // Web & Content Intents
    fun openWebPage(context: Context, url: String = "https://developer.android.com") {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Browser not available")
        }
    }
    
    fun openMaps(context: Context, location: String = "37.7749,-122.4194") {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$location?q=San+Francisco"))
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Maps app not available")
        }
    }
    
    fun searchOnMap(context: Context, query: String = "Restaurants near me") {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query"))
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Maps app not available")
        }
    }
    
    fun openYouTube(context: Context, videoId: String = "dQw4w9WgXcQ") {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
            intent.putExtra("force_fullscreen", true)
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to web browser
            openWebPage(context, "https://www.youtube.com/watch?v=$videoId")
        }
    }
    
    // Media Intents
    fun openCamera(context: Context) {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Camera not available")
        }
    }
    
    fun openGallery(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Gallery not available")
        }
    }
    
    fun recordVideo(context: Context) {
        try {
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Video recording not available")
        }
    }
    
    fun recordAudio(context: Context) {
        try {
            val intent = Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION)
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Audio recording not available")
        }
    }
    
    // System Intents
    fun openSettings(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Settings not available")
        }
    }
    
    fun openAppSettings(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.parse("package:${context.packageName}")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "App settings not available")
        }
    }
    
    fun openWifiSettings(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "WiFi settings not available")
        }
    }
    
    fun openContacts(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Contacts not available")
        }
    }
    
    fun addContact(context: Context, name: String = "John Doe", phone: String = "1234567890") {
        try {
            val intent = Intent(Intent.ACTION_INSERT).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
                putExtra(ContactsContract.Intents.Insert.NAME, name)
                putExtra(ContactsContract.Intents.Insert.PHONE, phone)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Cannot add contact")
        }
    }
    
    // Calendar Intents
    fun createCalendarEvent(context: Context, title: String = "Demo Event", location: String = "Demo Location") {
        try {
            val intent = Intent(Intent.ACTION_INSERT).apply {
                data = CalendarContract.Events.CONTENT_URI
                putExtra(CalendarContract.Events.TITLE, title)
                putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + 60 * 60 * 1000)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Calendar not available")
        }
    }
    
    fun setAlarm(context: Context, hour: Int = 8, minute: Int = 0) {
        try {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, hour)
                putExtra(AlarmClock.EXTRA_MINUTES, minute)
                putExtra(AlarmClock.EXTRA_MESSAGE, "Demo Alarm")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Alarm not available")
        }
    }
    
    // Sharing Intents
    fun shareText(context: Context, text: String = "Check out this Intent Demo app!") {
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
                putExtra(Intent.EXTRA_SUBJECT, "Intent Demo")
            }
            context.startActivity(Intent.createChooser(intent, "Share via"))
        } catch (e: Exception) {
            showError(context, "Sharing not available")
        }
    }
    
    fun shareMultipleText(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                type = "text/plain"
                putStringArrayListExtra(Intent.EXTRA_TEXT, arrayListOf(
                    "First message from Intent Demo",
                    "Second message from Intent Demo"
                ))
            }
            context.startActivity(Intent.createChooser(intent, "Share multiple via"))
        } catch (e: Exception) {
            showError(context, "Sharing not available")
        }
    }
    
    // File & Document Intents
    fun openDocument(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                type = "*/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "File picker not available")
        }
    }
    
    fun createDocument(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                type = "text/plain"
                addCategory(Intent.CATEGORY_OPENABLE)
                putExtra(Intent.EXTRA_TITLE, "demo_file.txt")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "File creation not available")
        }
    }
    
    // Search Intents
    fun searchWeb(context: Context, query: String = "Android Intents") {
        try {
            val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                putExtra("query", query)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            showError(context, "Web search not available")
        }
    }
    
    // Store Intent
    fun openPlayStore(context: Context, packageName: String = "com.example.intent") {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to web browser
            openWebPage(context, "https://play.google.com/store/apps/details?id=$packageName")
        }
    }
    
    private fun showError(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

