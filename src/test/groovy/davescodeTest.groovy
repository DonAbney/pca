import groovy.util.GroovyTestCase
   
class DavescodeTest extends GroovyTestCase {
    
    void testFailingTest() {
        Davescode myExample = new Davescode()
        assertTrue(myExample.fail())
    }
}
