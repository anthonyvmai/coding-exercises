import scala.io.Source

object Two {

    case class Item(name: String, price: Int) {
        override def toString: String = s"$name $price"
    }

    case class ItemPair(first: Item, second: Item) {
        override def toString: String = s"$first, $second"
        def price(): Int = first.price + second.price
    }

    def main(args: Array[String]): Unit = {
        val fileName = args(0)
        val cardBalance = args(1).toInt

        val items = Source.fromFile(fileName).getLines().map { line =>
            val splitLine = line.split(", ")
            Item(splitLine(0), splitLine(1).toInt)
        }.toArray

        items.indices
            .map { itemIndex => // map each item to option of pair of itself plus its complement
                val item = items(itemIndex)
                val complement = binarySearch(items, cardBalance - item.price, itemIndex + 1, items.length)
                complement.map(ItemPair(item, _))
            }
            .reduce { (maxPair, currPair) => // get the pair with the highest price
                (maxPair, currPair) match {
                    case (Some(mp), Some(cp)) if cp.price() > mp.price() => currPair
                    case (None, Some(cp)) => currPair
                    case (_, _) => maxPair
                }
            } match {
                case Some(p) => println(p)
                case None => println("Not possible")
            }
    }

    // return an option of the most expensive item in `arr` within the bounds
    // of `leftIndex` (inclusive) and `rightIndex` (exclusive) that is
    // cheaper than the item at `target`
    def binarySearch(arr: Array[Item], target: Int, leftIndex: Int, rightIndex: Int): Option[Item] = {
        if (leftIndex >= arr.length) {
            None
        } else if (leftIndex >= rightIndex) {
            val leftItem = arr(leftIndex)
            if (leftItem.price <= target) {
                Some(leftItem)
            } else {
                None
            }
        } else {
            val midIndex = (leftIndex + rightIndex) / 2
            val midItem = arr(midIndex)
            if (midItem.price > target) {
                binarySearch(arr, target, leftIndex, midIndex)
            } else {
                // if right search returns nothing, return current item
                binarySearch(arr, target, midIndex + 1, rightIndex).orElse(Some(midItem))
            }
        }
    }
}
