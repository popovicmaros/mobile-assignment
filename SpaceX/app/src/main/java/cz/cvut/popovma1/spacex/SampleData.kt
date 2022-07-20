package cz.cvut.popovma1.spacex

object SampleData {
    val conversationSample: List<MainActivity.Message> = (1..10).map {
        MainActivity.Message("author$it", "message$it: ${"Hello world! ".repeat(it*2)}")
    }
}
