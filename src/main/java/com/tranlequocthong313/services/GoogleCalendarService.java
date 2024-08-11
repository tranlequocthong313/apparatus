package com.tranlequocthong313.services;

import com.google.api.services.calendar.Calendar;
import com.tranlequocthong313.models.Maintenance;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleCalendarService {
	void createEvent(Maintenance maintenance);
}
