class DogTest extends GroovyTestCase {
    void testDogSpeak() {
        assertEquals('woof', new Dog().bark())
    }
}
