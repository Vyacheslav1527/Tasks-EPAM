package ua.nure.kulychenko.practice6.part1;

public class Word {
    private String content;
    private int frequency;

    public Word(String content, int frequency) {
        this.content = content;
        this.frequency = frequency;
    }

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String toLetterCountString() {
        return getContent().concat(" ==> ").concat(getContent().length() + "");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() == this.getClass()) {
            return content.equals(((Word) obj).getContent());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getContent().hashCode() * 37;
    }

    @Override
    public String toString() {
        return getContent().concat(" ==> ").concat(getFrequency() + "");
    }

}
