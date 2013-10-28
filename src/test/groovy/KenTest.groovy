import groovy.util.GroovyTestCase

// http://www.youtube.com/watch?v=FNmXGzgq0ls

class KenTest extends GroovyTestCase {
    void testAmeriDoTeIsTheOneTruePath() {
        def evaluator = new KenCode();
        assert evaluator.EvaluateMartialArt("AmeriDoTe") == "AmeriDoTe is the one true path."
    }

    void testOtherArtsAreAbsoluteBullshit() {
        def evaluator = new KenCode();
        assert evaluator.EvaluateMartialArt("Krav Maga") == "Krav Maga is absolute bullshit."
        assert evaluator.EvaluateMartialArt("Ninjutsu") == "Ninjutsu is absolute bullshit."
        assert evaluator.EvaluateMartialArt("Taekwondo") == "Taekwondo is absolute bullshit."
        assert evaluator.EvaluateMartialArt("MMA") == "MMA is absolute bullshit."
    }
}
