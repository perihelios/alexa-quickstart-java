package com.example;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

import static java.util.Collections.singleton;

@SuppressWarnings("unused")
public class ColorSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
	private static final String PROPERTIES_FILE = "app-id.properties";

	public ColorSpeechletRequestStreamHandler() {
		super(new ColorSpeechlet(), singleton(getAppId()));
	}

	private static String getAppId() {
		Properties properties = new Properties();

		try (InputStream stream = ColorSpeechletRequestStreamHandler.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
			if (stream == null) {
				throw new IllegalStateException(PROPERTIES_FILE + " not found on classpath");
			}

			properties.load(stream);
		} catch (IOException e) {
			throw new UncheckedIOException("Failed to load " + PROPERTIES_FILE + " from classpath", e);
		}

		String appId = properties.getProperty("app-id");

		if (appId == null || appId.isEmpty()) {
			throw new IllegalStateException("app-id property wasn't found in " + PROPERTIES_FILE);
		}

		return appId;
	}
}
