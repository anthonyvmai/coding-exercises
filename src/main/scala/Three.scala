object Three {

    def main(args: Array[String]): Unit = {
        val str = args(0)

        val replaceChar = 'X'
        val allowedChars = Seq('0', '1')

        combinations(str, replaceChar, allowedChars).foreach(println)
    }

    // finds all possible combinations of `originalStr` where
    // `replaceChar` is replaced by each char in `allowedChars`
    def combinations(originalStr: String, replaceChar: Char, allowedChars: Seq[Char]): Seq[String] = {

        // helper method where `replaceChar` and `allowedChars` are
        // in scope and don't need to be passed recursively
        def combinations(str: String): Seq[String] = {
            str match {
                case s: String if !s.contains(replaceChar) =>
                    Seq(s)
                case s: String =>
                    val replaceIndex = str.indexOf(replaceChar)
                    val beforeReplace = str.substring(0, replaceIndex)
                    val afterReplace = str.substring(replaceIndex + 1, str.length)

                    val suffixes = combinations(afterReplace)
                    allowedChars flatMap { c =>
                        suffixes.map(beforeReplace + c + _)
                    }
            }
        }

        combinations(originalStr)
    }
}
