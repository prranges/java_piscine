package preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String preprocessing(String str) {
        return str.toUpperCase();
    }
}
