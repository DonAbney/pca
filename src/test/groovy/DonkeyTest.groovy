import groovy.util.GroovyTestCase

class DonkeyTest extends GroovyTestCase {

    def donkey

    void setUp() {
        donkey = new Donkey()
    }

    void testGetShouldReturnADonkeyWithId1WhenIdPassedIs1() {
        assertEquals(1, donkey.get(1).id)
    }
}
