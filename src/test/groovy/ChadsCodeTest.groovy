

class ChadsCodeTest extends GroovyTestCase {


    void testReverseIsNotNull(){
        ChadsCode chadsCode = new ChadsCode()
        def aList = []
        assert !chadsCode.reverse(aList)
    }

    void testReverseReturnsCorrectSize(){
        ChadsCode chadsCode = new ChadsCode()
        def aList = [1,2,3,4]
        def result = chadsCode.reverse(aList)
        assert result.size() == 4

    }

    void testReverseReturnsListInCorrectOrder(){
        ChadsCode chadsCode = new ChadsCode()
        def aList = [1,2,3,4]
        def result = chadsCode.reverse(aList)
        assert result  == [4,3,2,1]

    }


}
