import groovy.util.GroovyTestCase
   
class ExampleTest extends GroovyTestCase {
    
    void testFailingTest() {
        Example myExample = new Example()
        assertTrue(myExample.fail())
    }
}
