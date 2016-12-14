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
