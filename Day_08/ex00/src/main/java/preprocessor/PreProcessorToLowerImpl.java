package preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String preprocessing(String str) {
        return str.toLowerCase();
    }
}
