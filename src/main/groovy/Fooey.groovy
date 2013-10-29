/**
 * ovi 10/29/13 7:29 AM 
 */
class Fooey {

    private int use;

    Fooey() {
        use = 0;
    }

    def useCount() {
        use
    }

    def useMe() {
        use += 1;
        if (use > 2) {
            throw new RuntimeException("Greedy User")
        }

    }
}
