package sample.models.enumerations;

import java.util.Arrays;

public enum AUDIO {
  DEFAULT("null"),
  DOLBY_SURROUND("Dolby Digital Surround"),
  DOLBY_PLUS("Dolby digital plus");

  private final String audio;

  AUDIO(String s) {
    audio = s;
  }
  public String getAudio() {
    return audio;
  }

  @Override
  public String toString() {
    return audio;
  }

  public static AUDIO getEnumByString(String audio){
    var enumAudio = Arrays.stream(AUDIO.values()).filter(src->src.toString().equals(audio)).findFirst();
    return enumAudio.orElse(null);
  }

}

