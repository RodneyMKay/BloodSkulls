import java.util.UUID;

public class UuidGenerator {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(UUID.randomUUID());
        }
    }
}
