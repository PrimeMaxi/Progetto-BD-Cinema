package sample.models.enumerations;

public enum AUDIO {
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
}

