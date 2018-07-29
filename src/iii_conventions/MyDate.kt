package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (this.year > other.year) return 1
        if (this.year < other.year) return -1
        if (this.month > other.month) return 1
        if (this.month < other.month) return -1
        if (this.dayOfMonth > other.dayOfMonth) return 1
        if (this.dayOfMonth < other.dayOfMonth) return -1
        return 0
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return MyDateIterator(start, endInclusive)
    }
}

private class MyDateIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
    var current: MyDate = start

    override fun hasNext(): Boolean {
        return current <= endInclusive;
    }

    override fun next(): MyDate {
        return current.apply {
            current = current.nextDay()
        }
    }
}