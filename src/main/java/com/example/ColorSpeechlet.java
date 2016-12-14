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

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorSpeechlet implements Speechlet {
	private static final Logger LOG = LoggerFactory.getLogger(ColorSpeechlet.class);

	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
		LOG.info("Started session (request ID {}, session ID {})", request.getRequestId(), session.getSessionId());
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		Card card = new SimpleCard();
		card.setTitle("Favorite Color");

		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText("Tell me your favorite color.");

		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		LOG.info("Asking person their favorite color");

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		Intent intent = request.getIntent();
		String color = intent.getSlot("color").getValue();

		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText("Ah, I like " + color + " too!");

		LOG.info("Telling person I also like their color, " + color);

		return SpeechletResponse.newTellResponse(speech);
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
		LOG.info("Ended session (request ID {}, session ID {})", request.getRequestId(), session.getSessionId());
	}
}
