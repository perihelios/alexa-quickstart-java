/*
 Copyright 2016, Perihelios LLC

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package com.example;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

import static java.util.Collections.singleton;

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
