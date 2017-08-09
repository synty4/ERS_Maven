/** 
 * This class uses Freetts's voice library to convert text into audio
 * @author: http://moderntone.blogspot.be/2013/02/freetts-tutorial.html
 * This class converts text to speech
 * 
 * **/
package be.ucl.ingi.lingi2252.utils;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
 
public class TextToSpeechConverter {
 
 private static final String VOICENAME_kevin = "kevin";
 private String text; // string to speech
 
 public TextToSpeechConverter(String text) {
	 this.text = text;
 }
 
 public void speak() {
	 Voice voice;
	 VoiceManager voiceManager = VoiceManager.getInstance();
	 voice = voiceManager.getVoice(VOICENAME_kevin);
	 voice.allocate();
	 voice.speak(text);
 }
 
}