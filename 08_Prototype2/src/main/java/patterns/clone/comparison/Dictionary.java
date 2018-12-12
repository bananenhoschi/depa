package patterns.clone.comparison;

@SuppressWarnings("unused")
public class Dictionary implements Cloneable {
	private String language;
	private final int size;
	private String[] words;
	private Dictionary clone;

	public Dictionary(String language, int size) {
		this.language = language;
		this.size = size;
		this.words = new String[size];
		for (int i = 0; i < size; i++)
			this.words[i] = "sample word " + i;
	}

	public Dictionary(Dictionary d){
        if(d == null){
            throw new RuntimeException();
        }
		this.language = d.language;
		this.size = d.size;
		this.words = d.words.clone();
	}

	@Override
	public Dictionary clone() {
		return new Dictionary(this);
	}
}
