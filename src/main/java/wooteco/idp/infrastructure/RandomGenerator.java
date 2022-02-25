package wooteco.idp.infrastructure;

import java.util.Random;

public class RandomGenerator {

    private static final Random RANDOM = new Random();

    private RandomGenerator() {
        throw new IllegalStateException("RandomGenerator는 객체를 생성할 수 없습니다.");
    }

    public static String generateKey(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        return RANDOM.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
