import groovy.util.GroovyTestCase

class DonkeyTest extends GroovyTestCase {

    def donkey

    void setUp() {
        donkey = new Donkey()
    }

    void testGetDonkeyShouldReturnADonkey() {
        assertNotNull(donkey.get(1))
    }
}
