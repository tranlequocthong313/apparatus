package com.tranlequocthong313.services.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.tranlequocthong313.models.DaysOfWeek;
import com.tranlequocthong313.models.Maintenance;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.services.DaysOfWeekService;
import com.tranlequocthong313.services.DeviceService;
import com.tranlequocthong313.services.GoogleCalendarService;
import com.tranlequocthong313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GoogleCalendarServiceImpl implements GoogleCalendarService {
	private static final String APPLICATION_NAME = "Apparatus";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String TIMEZONE = "Asia/Ho_Chi_Minh";
	private static final String calendarId = "2837bb3e1ba0eeff0d79f82bebcf05e807a82608c0bd515e619131de2137a67e@group.calendar.google.com";

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private UserService userService;
	@Autowired
	private DaysOfWeekService daysOfWeekService;
	@Autowired
	private Credential credential;

	private Calendar getCalendarService() throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
			.setApplicationName(APPLICATION_NAME)
			.build();
	}

	@Override
	public void createEvent(Maintenance maintenance) {
		try {
			Calendar service = getCalendarService();
			Event event = new Event()
				.setSummary(maintenance.getSummary())
				.setLocation(deviceService.findById(maintenance.getDevice().getId()).getLocation().getAddress())
				.setDescription(maintenance.getDescription());

			EventDateTime start = new EventDateTime();
			if (maintenance.getAllDay()) {
				DateTime startDate = new DateTime(true, maintenance.getStartDate().getTime(), 7);
				start.setDate(startDate);
			} else {
				DateTime startDateTime = new DateTime(maintenance.getStartDateTime());
				start
					.setDateTime(startDateTime)
					.setTimeZone(TIMEZONE);
			}
			event.setStart(start);

			EventDateTime end = new EventDateTime();
			if (maintenance.getAllDay()) {
				DateTime endDate = new DateTime(true, maintenance.getEndDate().getTime(), 7);
				end.setDate(endDate);
			} else {
				DateTime endDateTime = new DateTime(maintenance.getEndDateTime());
				end
					.setDateTime(endDateTime)
					.setTimeZone(TIMEZONE);
			}
			event.setEnd(end);

			if (maintenance.getRecurrenceType() != Maintenance.RecurrenceType.NONE) {
				List<String> recurrences = new ArrayList<>();
				String recurrence = "";

				if (maintenance.getRecurrenceType() == Maintenance.RecurrenceType.DAILY) {
					recurrence += "RRULE:FREQ=DAILY;INTERVAL=" + maintenance.getRepeatEvery();
				} else if (maintenance.getRecurrenceType() == Maintenance.RecurrenceType.WEEKLY) {
					StringJoiner days = new StringJoiner(",");
					maintenance.getDaysOfWeekSet().forEach(d -> {
						DaysOfWeek day = daysOfWeekService.mapToDaysOfWeek(daysOfWeekService.findById(d.getId()));
						days.add(day.getKey());
					});
					recurrence += "RRULE:FREQ=WEEKLY;INTERVAL=" + maintenance.getRepeatEvery() + ";BYDAY=" + days;
				} else if (maintenance.getRecurrenceType() == Maintenance.RecurrenceType.MONTHLY) {
					recurrence += "RRULE:FREQ=MONTHLY;INTERVAL=" + maintenance.getRepeatEvery();
				} else if (maintenance.getRecurrenceType() == Maintenance.RecurrenceType.YEARLY) {
					recurrence += "RRULE:FREQ=YEARLY;INTERVAL=" + maintenance.getRepeatEvery();
				}

				if (maintenance.getEndRecurrenceType() == Maintenance.EndRecurrenceType.ON_DATE) {
					SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
					String isoString = isoFormat.format(maintenance.getEndDateRecurrence());
					isoString = isoString.replaceAll("[-:]", "");
					isoString = isoString.substring(0, isoString.indexOf('.')) + "Z";
					recurrence += ";UNTIL=" + isoString;
				} else if (maintenance.getEndRecurrenceType() == Maintenance.EndRecurrenceType.AFTER_OCCURRENCES) {
					recurrence += ";COUNT=" + maintenance.getOccurrenceCount();
				}

				recurrences.add(recurrence);
				event.setRecurrence(recurrences);
			}

			if (!maintenance.getUserSet().isEmpty()) {
				List<EventAttendee> attendees = new ArrayList<>();
				maintenance.getUserSet().forEach(u -> {
					User user = userService.findById(u.getId());
					attendees.add(new EventAttendee()
						.setDisplayName(user.getFullName())
						.setEmail(user.getEmail())
					);
				});
				event.setAttendees(attendees);
			}

			List<EventReminder> reminderOverrides = new ArrayList<>();
			reminderOverrides.add(new EventReminder().setMethod("email").setMinutes(24 * 60));
			reminderOverrides.add(new EventReminder().setMethod("popup").setMinutes(10));
			Event.Reminders reminders = new Event.Reminders()
				.setUseDefault(false)
				.setOverrides(reminderOverrides);
			event.setReminders(reminders);

			event = service.events().insert(calendarId, event).execute();

			System.out.printf("Event created: %s\n", event.getHtmlLink());

		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
	}
}

