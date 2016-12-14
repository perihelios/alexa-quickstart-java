# Alexa Quickstart for Java

Create and deploy your first Amazon Echo (Alexa) Skill in five minutes or less!

After deploying your skill and configuring it in the Amazon Developer portal, you will be able to hold a simple conversation with Alexa:

> You: "Alexa, ask my favorite color."
> 
> Alexa: "Tell me your favorite color."
> 
> You: "Red."
> 
> Alexa: ...

Alexa's brain is in the cloud, and your Alexa Skill will be no different. It will be deployed to AWS Lambda.

## Prerequisites

To successfully deploy and use your skill:
 
 1. Your Echo (or Echo Dot) must be physically set up and connected to the Internet, and associated with your Amazon account.
 1. AWS must be usable with your (same) Amazon account, and you need to have your `$HOME/.aws/credentials` configured appropriately.
 1. A Java 8 JDK must be installed and configured appropriately for your system (e.g., `JAVA_HOME` set, `$JAVA_HOME/bin` on your path).

## Quick Steps

If you're fairly good at navigating through Web interfaces (like the [Amazon Developer portal](https://developer.amazon.com/) we'll be using), you can just follow the simple textual steps here. If you need more help, including screenshots, go to [Full Steps](#full-steps).

 1. Go to your [Alexa Skills Kit list in the Amazon Developer portal](https://developer.amazon.com/edw/home.html#/skills/list).
 1. Click *Add a New Skill*.
 1. Enter **Favorite Color** as the *Name*, and **my favorite color** as the *Invocation Name*.
 1. Click Next.
 1. Copy your skill's app ID (near the top of the screen - it looks like `amzn1.ask.skill.xxxxx`) and paste it into the [src/main/resources/app-id.properties](src/main/resources/app-id.properties) file in your Java project.
 1. From a command prompt, in your Java project's directory, run `./gradlew alexaDeploySkill`.
 1. Record the *Lambda ARN* printed by the Gradle build script; you'll need it later.
 1. Back in the Amazon Developer portal, using the [skill-data.txt](skill-data.txt) file in your Java project as a source for copying data:
    1. Copy and paste the *Intent Schema* JSON.
    1. Add a new *Custom Slot Type* named `COLOR`; copy and paste the list of colors.
    1. Copy and paste the *Sample Utterance*.
    1. Click *Next* and wait for the model to build and the next page to appear.
 1. Select *AWS Lambda ARN* and check *North America*, and paste the Lambda ARN printed by the Gradle script into the text box.
 1. Click *Next*.

That's it! Now you can talk with Alexa. (The first time you access a new skill, it can take a few seconds, so don't be alarmed if she doesn't respond immediately the first time.)

Say: **Alexa, ask my favorite color**. Then just listen and respond!

## Full Steps

**TODO!**

## License

Alexa Quickstart for Java is licensed under the Apache 2.0 License - see the [LICENSE.txt](LICENSE.txt) and [NOTICE.txt](NOTICE.txt) files for details.
