# Android Notification Types Reference

Complete guide to all notification types implemented in this app.

## 1. Basic Notification 📱

**What it is**: Simple notification with icon, title, and short text.

**When to use**:
- Quick alerts
- Simple messages
- Status updates

**Features**:
- Small icon (required)
- Title
- Short text content
- Tap to open app
- Auto-dismiss on tap

**Example Use Cases**:
- "Message received"
- "Task completed"
- "New update available"

---

## 2. Big Text Notification 📄

**What it is**: Expandable notification that shows long text when expanded.

**When to use**:
- Long messages that don't fit in basic notification
- Email content
- Article previews
- Detailed status information

**Features**:
- Collapsed: Shows preview text
- Expanded: Shows full content
- Summary text
- Expandable title

**Example Use Cases**:
- Email body
- SMS/chat messages
- News article preview
- Long status messages

---

## 3. Big Picture Notification 🖼️

**What it is**: Notification that displays a large image.

**When to use**:
- Photo sharing apps
- Image messages
- Visual content
- Map previews

**Features**:
- Large image display when expanded
- Thumbnail when collapsed
- Image can be tapped
- Summary text below image

**Example Use Cases**:
- "John sent you a photo"
- "New image uploaded"
- "Map location shared"
- Screenshot capture complete

---

## 4. Inbox Style Notification 📧

**What it is**: Shows a list of multiple items in an inbox-like format.

**When to use**:
- Multiple messages
- List of items
- Grouped information
- Summary of events

**Features**:
- Multiple lines (up to 7)
- Count of items
- Summary text
- Each line can be different

**Example Use Cases**:
- "5 new emails"
- Multiple text messages
- Multiple calendar events
- List of notifications

---

## 5. Action Buttons Notification ⚡

**What it is**: Notification with interactive action buttons.

**When to use**:
- Quick actions needed
- User response required
- Multiple options available

**Features**:
- Up to 3 action buttons
- Icons for each action
- Actions work without opening app
- Can trigger BroadcastReceivers

**Example Use Cases**:
- "Like" / "Share" buttons
- "Reply" / "Delete" for messages
- "Accept" / "Decline" for calls
- "Play" / "Pause" for media

---

## 6. Progress Notification 📊

**What it is**: Shows a progress bar indicating task completion.

**When to use**:
- File downloads
- File uploads
- Processing tasks
- Long-running operations

**Features**:
- Determinate progress (0-100%)
- Indeterminate progress (unknown duration)
- Ongoing (non-dismissible while active)
- Auto-updates percentage
- Can be cancelled

**Example Use Cases**:
- File download: "Downloading... 45%"
- File upload: "Uploading photo..."
- Processing: "Converting video..."
- Syncing: "Syncing data..."

---

## 7. Heads-Up Notification 🔔

**What it is**: High-priority notification that appears at the top of screen.

**When to use**:
- Urgent alerts
- Incoming calls
- Alarms
- Time-sensitive information

**Features**:
- Appears over current screen
- Full-screen intent option
- High priority
- Sound and vibration
- Auto-dismisses after few seconds

**Example Use Cases**:
- Incoming call
- Alarm/timer
- Urgent security alert
- Emergency notification

**Requirements**:
- Must use HIGH priority
- Should set category (e.g., CATEGORY_ALARM)
- Doesn't work in Do Not Disturb mode

---

## 8. Grouped Notifications 📚

**What it is**: Multiple related notifications grouped together.

**When to use**:
- Multiple messages from same app
- Batch of related items
- Prevent notification spam
- Keep notification shade organized

**Features**:
- Individual notifications
- Summary notification
- Expands to show all items
- Collapses to save space
- Can dismiss individually or all at once

**Example Use Cases**:
- Multiple chat messages
- Multiple emails
- Multiple app updates
- Multiple calendar reminders

---

## 9. Custom Styled Notification 🎨

**What it is**: Notification with custom colors and styling.

**When to use**:
- Brand identity
- Color-coded categories
- Visual distinction
- Special events

**Features**:
- Custom background color
- Colorized notification
- Custom accent color
- Matches app branding

**Example Use Cases**:
- Color-coded priority (red=urgent, yellow=warning)
- Brand colors for marketing
- Category colors (blue=work, green=personal)
- Special occasion themes

---

## 10. Media Style Notification 🎵

**What it is**: Special notification for media playback controls.

**When to use**:
- Music/audio playback
- Video playback
- Podcast players
- Audio recording

**Features**:
- Media controls (play, pause, next, previous)
- Album artwork
- Track information
- Compact view with controls
- Ongoing (stays until dismissed)
- Shows on lock screen

**Example Use Cases**:
- Music player: "Now Playing: Song Title"
- Podcast: "Playing: Episode Name"
- Audio recording: "Recording..."
- YouTube playback

**Best Practices**:
- Use MediaSession for full functionality
- Keep ongoing while playing
- Update when track changes
- Show proper artwork

---

## 11. Scheduled Notification ⏰

**What it is**: Notification that appears after a delay or at specific time.

**When to use**:
- Reminders
- Delayed alerts
- Scheduled messages
- Time-based triggers

**Features**:
- Appears at specified time
- Runs even if app is closed
- Survives device reboot (with permission)
- Can be cancelled before showing
- Uses WorkManager for reliability

**Example Use Cases**:
- Reminder: "Meeting in 15 minutes"
- Alarm: "Wake up!"
- Medication reminder
- Scheduled task completion

**Implementation**:
- Uses WorkManager
- Can set exact time or delay
- Persists across app restarts
- Handles device sleep

---

## 12. Push Notification (FCM) 🌐

**What it is**: Notification sent from server via Firebase Cloud Messaging.

**When to use**:
- Server-triggered alerts
- Real-time updates
- Messages from other users
- Remote notifications

**Features**:
- Sent from server
- Works when app is closed
- Can include data payload
- Supports topics and targeting
- Delivery reports available

**Example Use Cases**:
- Chat messages
- Social media notifications
- Breaking news alerts
- Order status updates
- Marketing messages

**Setup Required**:
- Firebase project
- google-services.json
- Server implementation
- FCM token management

---

## Notification Priority Levels

### HIGH
- Heads-up notifications
- Urgent alerts
- Incoming calls
- **Sound**: Yes
- **Vibration**: Yes
- **Heads-up**: Yes

### DEFAULT
- Most notifications
- Messages
- Updates
- **Sound**: Yes
- **Vibration**: Possible
- **Heads-up**: No

### LOW
- Progress notifications
- Background tasks
- Silent updates
- **Sound**: No
- **Vibration**: No
- **Heads-up**: No

---

## Notification Channels (Android 8.0+)

Each notification belongs to a channel that users can control:

### Default Channel
- General notifications
- Importance: MEDIUM
- Used for most notifications

### Important Channel
- High-priority alerts
- Importance: HIGH
- Sound + Vibration + Lights

### Progress Channel
- Downloads/uploads
- Importance: LOW
- Silent notifications

### Media Channel
- Media playback
- Importance: MEDIUM
- Lock screen visibility

**User Control**:
Users can:
- Turn channels on/off
- Change importance
- Override sound
- Disable vibration
- Hide from lock screen

---

## Best Practices

### DO ✅
- Use appropriate notification type for content
- Set proper priority
- Provide action buttons when useful
- Use notification channels correctly
- Test on different Android versions
- Respect user's notification settings
- Update progress notifications regularly
- Group related notifications
- Clear outdated notifications

### DON'T ❌
- Spam users with too many notifications
- Use HIGH priority for non-urgent items
- Ignore notification permission (Android 13+)
- Forget to set small icon (required)
- Use inappropriate sounds
- Show sensitive info on lock screen
- Keep old notifications forever
- Use all caps in text
- Forget to handle notification clicks

---

## Testing Checklist

Test each notification type:

- [ ] Appears correctly
- [ ] Shows proper icon and text
- [ ] Expands/collapses correctly (if expandable)
- [ ] Actions work as expected
- [ ] Tap opens correct screen
- [ ] Auto-cancel works (if enabled)
- [ ] Respects Do Not Disturb
- [ ] Works on different Android versions
- [ ] Looks good on light/dark theme
- [ ] Shows on lock screen appropriately
- [ ] Groups correctly (if grouped)
- [ ] Updates properly (if updating)

---

## Quick Reference Table

| Type | Expandable | Actions | Priority | Use Case |
|------|-----------|---------|----------|----------|
| Basic | No | Optional | Any | Simple alerts |
| Big Text | Yes | Optional | Any | Long messages |
| Big Picture | Yes | Optional | Any | Images |
| Inbox | Yes | Optional | Any | Lists |
| Actions | No | Yes | Any | Quick responses |
| Progress | No | Optional | LOW | Downloads |
| Heads-Up | No | Optional | HIGH | Urgent |
| Grouped | Yes | Optional | Any | Multiple items |
| Custom | No | Optional | Any | Branding |
| Media | No | Yes | DEFAULT | Playback |
| Scheduled | No | Optional | Any | Reminders |
| Push (FCM) | No | Optional | Any | Remote alerts |

---

## Resources

- [Android Notification Documentation](https://developer.android.com/develop/ui/views/notifications)
- [Material Design - Notifications](https://material.io/design/platform-guidance/android-notifications.html)
- [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging)
- [Notification Channels](https://developer.android.com/develop/ui/views/notifications/channels)

