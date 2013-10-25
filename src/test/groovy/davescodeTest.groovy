import groovy.util.GroovyTestCase
   
class DavescodeTest extends GroovyTestCase {
    
    void testFailingTest() {
        Davescode myExample = new Davescode()
        assertTrue(myExample.fail())
    }
        
    void testWhenTwoPassedInShouldGetFourBack() {
        Davescode myExample = new Davescode()
        assertEquals(4, myExample.convert(2))
    }

    void testWhenThreePassedInShouldGetNineBack() {
        Davescode myExample = new Davescode()
        assertEquals(9, myExample.convert(3))
    }

    void testWhenFourPassedInShouldGetSixteenBack() {
        Davescode myExample = new Davescode()
        assertEquals(16, myExample.convert(4))
    }

}
