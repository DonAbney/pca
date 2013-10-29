import groovy.util.GroovyTestCase

/**
 * ovi 10/29/13 7:20 AM 
 */
class FooeyTest extends GroovyTestCase {

    private Fooey foo;

    void setUp() {
        foo = new Fooey();
    }

    void testFooeyIsInitializedAndNotUsedWhenCreated() {
        assertTrue(0 == foo.useCount());
    }

    void testFooeyGetsUsedOnce() {
        foo.useMe();
        assertEquals(1, foo.useCount())
    }

    void testFooeyGetsUsedTwice() {
        foo.useMe();
        foo.useMe();
        assertEquals(2, foo.useCount());
    }


    void testJustTryUsingFooeyAgainIDareYou() {
        foo.useMe();
        foo.useMe();
        try {
            foo.useMe();
            fail("Should have thrown exception for overuse!")
        } catch(RuntimeException rte) {
            assertEquals("Greedy User", rte.getMessage())
        }
    }
}
