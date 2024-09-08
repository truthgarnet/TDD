package Chapter5_Junit5;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Junit5Test {

    public Junit5Test() {
        System.out.println("new LifecycleTest");
    }

    @BeforeAll
    @DisplayName("딱 한 번 실행됩니다.")
    void init() {

    }

    @BeforeEach
    @DisplayName("초기화")
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    @DisplayName("테스트A: Disabled은 @BeforeEach나 @AfterEach에 실행이 되지 않습니다.")
    @Disabled
    void a() {
        System.out.println("A");
    }

    @Test
    @DisplayName("테스트B")
    void b() {
        System.out.println("B");
    }

    @AfterEach
    @DisplayName("프로그램 종료")
    void tearDown() {
        System.out.println("tearDown");
    }

}
