package HashCoder;

public class HashCoder implements IHashCoder {
    @Override
    public int getHashCode(Object obj) {
        String str = (String) obj;

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = 31 * result + str.indexOf(i);
        }

        return result;
    }
}
